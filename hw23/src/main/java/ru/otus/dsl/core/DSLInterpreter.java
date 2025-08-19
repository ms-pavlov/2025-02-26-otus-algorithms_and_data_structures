package ru.otus.dsl.core;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import ru.otus.dsl.mapper.ExpressionObject;
import ru.otus.dsl.mapper.SemanticAnalyzer;
import ru.otus.dsl.parser.Parser;
import ru.otus.dsl.parser.TokenScanner;
import ru.otus.dsl.scaner.Search;
import ru.otus.expressions.services.PackageService;
import ru.otus.expressions.storage.ExpressionStorage;
import ru.otus.model.SyntaxNode;
import ru.otus.model.Token;
import ru.otus.model.enums.ScopePackages;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DSLInterpreter implements ApplicationListener<ContextRefreshedEvent> {

    private final ScriptFileScanner scriptsDir;
    private final Search search;
    private final Parser parser;
    private final SemanticAnalyzer analyzer;
    private final PackageService packageService;
    private final ExpressionStorage expressionStorage;
    private boolean isLoaded = false;

    public DSLInterpreter(
            ScriptFileScanner scriptsDir,
            @Qualifier("aho") Search search,
            @Qualifier("scriptParser") Parser parser,
            @Qualifier("scriptSemanticAnalyzer") SemanticAnalyzer analyzer,
            PackageService packageService, ExpressionStorage expressionStorage) {
        this.scriptsDir = scriptsDir;
        this.search = search;
        this.parser = parser;
        this.analyzer = analyzer;
        this.packageService = packageService;
        this.expressionStorage = expressionStorage;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (!isLoaded) {
            load();
            isLoaded = true;
        }
    }

    public void load() {
        List<File> files = scriptsDir.getFilesFromResources();

        for (File file : files) {
            try {
                String script = FileUtils.readFileToString(file);
                TokenScanner tokenScanner = new TokenScanner(
                        search.scan(script)
                                .stream()
                                .collect(Collectors.groupingBy(Token::getBegin)));

                SyntaxNode syntaxNode;

                syntaxNode = parser.parse(tokenScanner);

                while (null != syntaxNode) {
                    ExpressionObject expressionObject = analyzer.map(syntaxNode);
                    if (null != expressionObject) {
                        for (ScopePackages scopePackage : expressionObject.getScopePackages()) {
                            packageService.put(expressionObject.getExpressions(), scopePackage, expressionObject.getExpressionFactory());
                            expressionStorage.refresh();
                        }
                    }
                    syntaxNode = parser.parse(tokenScanner);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
