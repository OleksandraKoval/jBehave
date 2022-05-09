package apiTest;

import common.annotation.TestType;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;

import java.util.ArrayList;
import java.util.List;

public class TestTypeRunner extends SerenityRunner {

    public TestTypeRunner(Class<?> clazz) throws InitializationError {
        super(clazz);
    }

    @Override
    protected List<FrameworkMethod> computeTestMethods() {
        return new ArrayList<>(getTestClass().getAnnotatedMethods(TestType.class));
    }

}

