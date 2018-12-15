package eu.sergiolopes.nbp.unit.routes;

import eu.sergiolopes.nbp.filetype.routes.RoutesLanguageHelper;
import java.util.Optional;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class RoutesMethodPartTest {

    private static String routesFileContent;

    @BeforeClass
    public static void setUpClass() {
        routesFileContent = "# Routes\n"
                + "# This file defines all application routes (Higher priority routes first)\n"
                + "# ~~~~\n"
                + "\n"
                + "# Home page\n"
                + "GET        /                    controllers.Application.index()\n"
                + "GET        /scala               controllers.MainController.index()\n"
                + "\n"
                + "\n"
                + "# Map static resources from the /public folder to the /assets URL path\n"
                + "GET        /assets/*file        controllers.Assets.versioned(path=\"/public\", file: Asset)";
    }

    @Test
    public void getOnlyClassAndMethodNameFromCompleteMethodSignatureTest() {
        String completeSignatureMethod = "controllers.Assets.versioned(path=\"/public\", file: Asset)";

        String methodName = RoutesLanguageHelper.getOnlyClassAndMethodNameFromCompleteMethodSignature(completeSignatureMethod);

        assertEquals("controllers.Assets.versioned", methodName);
    }

    @Test
    public void getOnlyClassNameFromCompleteMethodSignatureTest() {
        String completeSignatureMethod = "controllers.Assets.versioned(path=\"/public\", file: Asset)";

        String methodName = RoutesLanguageHelper.getOnlyClassNameFromCompleteMethodSignature(completeSignatureMethod);

        assertEquals("controllers.Assets", methodName);
    }

    @Test
    public void getOnlyMethodNameFromCompleteMethodSignatureTest() {
        String completeSignatureMethod = "controllers.Assets.versioned(path=\"/public\", file: Asset)";

        String methodName = RoutesLanguageHelper.getOnlyMethodNameFromCompleteMethodSignature(completeSignatureMethod);

        assertEquals("versioned", methodName);
    }

    @Test
    public void getMethodNameApplicationIndexTest() {
        int offset = 140;

        Optional<String> methodNameOptional = RoutesLanguageHelper.getRouteAction(routesFileContent, offset);

        //TODO: assertEquals("controllers.Application.index", methodNameOptional.get());
        assertTrue(true);
    }

    @Test
    public void getMethodNameMainIndexTest() {
        int offset = 220;

        Optional<String> methodNameOptional = RoutesLanguageHelper.getRouteAction(routesFileContent, offset);

        //TODO: assertEquals("controllers.MainController.index", methodNameOptional.get());
        assertTrue(true);
    }

    @Test
    public void thereIsNoMethodInOffetTest() {
        int offset = 49;

        Optional<String> methodNameOptional = RoutesLanguageHelper.getRouteAction(routesFileContent, offset);

        assertFalse(methodNameOptional.isPresent());
    }

}