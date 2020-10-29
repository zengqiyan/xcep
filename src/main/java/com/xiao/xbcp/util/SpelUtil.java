package com.xiao.xbcp.util;

import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.lang.Nullable;

/**
 * Spring 表达式语言（SpEL）工具类
 * 
 * @author cobe
 *
 */
public class SpelUtil {
    private static final ExpressionParser expressionParser = new SpelExpressionParser();

    private SpelUtil() {}

    public static Object getValue(String expression) {
        return expressionParser.parseExpression(expression);
    }

    public static Object getValue(Object object, String expression) {
        // 解析上下文
        EvaluationContext context = new StandardEvaluationContext(object);
        return expressionParser.parseExpression(expression).getValue(context);
    }

    public static <T> T getValue(Object object, String expression, @Nullable Class<T> desiredResultType) {
        // 解析上下文
        EvaluationContext context = new StandardEvaluationContext(object);
        return expressionParser.parseExpression(expression).getValue(context, desiredResultType);
    }

    public static void setValue(Object object, String expression, Object value) {
        // 解析上下文
        EvaluationContext context = new StandardEvaluationContext(object);
        expressionParser.parseExpression(expression).setValue(context, value);
    }
}
