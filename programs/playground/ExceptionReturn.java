import java.util.HashMap;
import java.util.Map;

public class ExceptionReturn {
    public static void main(String[] args) throws Exception {

        ExceptionReturn exr = new ExceptionReturn();
        System.out.println(exr.getData(1));
        System.out.println(exr.getData(3));
        System.out.println(exr.getData(2));
    }

    public boolean getData(int id) throws Exception {
        String name = null;
        try {
            name = getNameForId(id);
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("");
        } finally {
            return name != null;
        }

    }

    public String getNameForId(int id) throws Exception {
        Map<Integer, String> dataStore = new HashMap<>();
        dataStore.put(1, "TikTok");
        dataStore.put(3, "FaceBook");
        if (!dataStore.containsKey(id)) {
            throw new Exception("Data not present");
        }
        return dataStore.get(id);
    }
}
