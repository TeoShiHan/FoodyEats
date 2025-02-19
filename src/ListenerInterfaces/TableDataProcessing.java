package ListenerInterfaces;

import java.util.ArrayList;
import java.util.HashMap;

public interface TableDataProcessing {
    public boolean isMatch(String str1, String str2);
    public ArrayList<String> getAllKeysInTable(ArrayList<HashMap<String,Object>>table);
    public HashMap<String,ArrayList<String>> getArrMapToKey(ArrayList<HashMap<String,Object>>table, String keyName, String ...fieldNames);
}
