package Menu;

import java.util.HashMap;

public class MenuDatabase {
    private static MenuDatabase database = null;
    private HashMap<String, String> jsons;
    private MenuDatabase() {
        jsons = new HashMap<String, String>();
        this.setJsons();
    }

    private void setJsons() {
        jsons.put("MainMenu", "{\n" +
                "  \"name\" : \"MainMenu\",\n" +
                "  \"menus\" : [\n" +
                "    \"StudentMenu\",\n" +
                "    \"TeacherMenu\"\n" +
                "  ]\n" +
                "}");
        jsons.put("StudentMenu", "{\n" +
                "  \"name\" : \"StudentMenu\",\n" +
                "  \"derivedMenu\" : \"MainMenu\"\n" +
                "}");
        jsons.put("TeacherMenu", "{\n" +
                "  \"name\" : \"TeacherMenu\",\n" +
                "  \"derivedMenu\" : \"MainMenu\"\n" +
                "}");
    }

    public static MenuDatabase getDatabase()
    {
        if(database == null)
            database = new MenuDatabase();
        return database;
    }

    public String getJson(String menuName)
    {
        return jsons.get(menuName);
    }
}
