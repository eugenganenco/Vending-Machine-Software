import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


public class Model {
    Controller controller;
    HashMap<String, ItemStock> itemStockHashMap;
    public Model(Controller controller){
        this.controller = controller;
        itemStockHashMap = new HashMap<>();
    }
    public boolean supplyItems(File file) {
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader(file.toString())) {
            Object obj = jsonParser.parse(reader);
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray itemsArray = (JSONArray)jsonObject.get("items");

            for (Object o : itemsArray) {
                JSONObject jsonItem = (JSONObject) o;

                if (!itemStockHashMap.containsKey((String)jsonItem.get("name"))) {
                    ItemStock itemStock = new ItemStock((String)jsonItem.get("name"),(Long) jsonItem.get("amount"),(String) jsonItem.get("price"));
                    itemStockHashMap.put(itemStock.getName(),itemStock);
                }
                else {
                    itemStockHashMap.get((String)jsonItem.get("name")).addItems((Long) jsonItem.get("amount"));
                }
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean storageIsEmpty(){
        return itemStockHashMap.isEmpty();
    }

    public void actualizePurchase(String itemName){
        saveTransaction(itemStockHashMap.get(itemName));
        Long currentAmount = itemStockHashMap.get(itemName).getAmount();
        if (currentAmount == 1) itemStockHashMap.remove(itemName);
        else itemStockHashMap.get(itemName).setNewAmount(currentAmount - 1);
    }

    public HashMap<String,ItemStock> getItemStockHashMap(){
        HashMap<String,ItemStock> itemStockCopyHashMap = new HashMap<>();
        for (String itemName:itemStockHashMap.keySet()) {
            itemStockCopyHashMap.put(itemName,itemStockHashMap.get(itemName).getItemStockCopy());
        }
        return itemStockCopyHashMap;
    }
    public double getTotalRevenue(){
        List<Transaction> transactionList = getTransactionList();

        double totalRevenue = 0D;
        for (Transaction transaction:transactionList) {
            String priceString = transaction.getPrice().substring(1);
            System.out.println(priceString);
            totalRevenue += Double.parseDouble(priceString);
        }
        totalRevenue = Math.round(totalRevenue*100.0)/100.0;

        return totalRevenue;

    }
    private void saveTransaction(ItemStock itemStock){
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader("transactions.json")){
            Object obj = jsonParser.parse(reader);
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray jsonArray = (JSONArray) jsonObject.get("transactions");
            JSONObject transaction = new JSONObject();
            transaction.put("name",itemStock.getName());
            transaction.put("price",itemStock.getPrice());
            SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
            Date date = new Date(System.currentTimeMillis());
            transaction.put("date",formatter.format(date));
            jsonArray.add(transaction);
            jsonObject = new JSONObject();
            jsonObject.put("transactions",jsonArray);

            try (FileWriter file = new FileWriter("transactions.json")) {
                file.write(jsonObject.toString());
                file.flush();
            }

        }
        catch (IOException | ParseException e) {
            e.printStackTrace();
        }

    }
    public List<Transaction> getTransactionList(){
        List<Transaction> transactionList = new ArrayList<>();
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader("transactions.json")) {
            Object obj = jsonParser.parse(reader);
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray itemsArray = (JSONArray)jsonObject.get("transactions");

            for (Object o : itemsArray) {
                JSONObject jsonItem = (JSONObject) o;
                transactionList.add(new Transaction((String)jsonItem.get("name"),(String)jsonItem.get("price"),(String)jsonItem.get("date")));
            }
            return transactionList;
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return null;


    }



}
