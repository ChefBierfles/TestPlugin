package nl.chefbierfles.testplugin.modules.Base;

public class BaseModule {

    public static boolean isModuleEnabled = true;

    public BaseModule() {}

    public static void ToggleModule() {
        isModuleEnabled = !isModuleEnabled;
    }
}
