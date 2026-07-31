package utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.ITestListener;
import org.testng.annotations.ITestAnnotation;
import org.testng.internal.annotations.IAnnotationTransformer;

public class SuiteListener implements ITestListener, IAnnotationTransformer{
	
	public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
		
		annotation.setRetryAnalyzer(RetryAnalyzer.class);
	}
	

}
