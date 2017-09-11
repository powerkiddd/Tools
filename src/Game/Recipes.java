package Game;

import java.util.ArrayList;
import java.util.List;

import Main.Crash;
import Main.ReadFromFile;

public class Recipes {
	
	public static List<String> Block = new ArrayList<String>(); //The block that has been clicked
	public static List<String> Item = new ArrayList<String>(); //The item that the block needs to be clicked with
	public static List<String> ResultItem = new ArrayList<String>(); //The item you'll get
	public static List<String> ResultAmount = new ArrayList<String>(); //The amount of the item you'll get
	public static List<String> ResultType = new ArrayList<String>(); //The type of the item you'll get
	public static List<Boolean> DestroyBlock = new ArrayList<Boolean>(); //Should the block be destroyed after interacting?
	
	public static void main(String[] args) {
		if (!LoadRecipeList()) {
			Crash.cause = "Failed to load recipe list, recovery not available.";
			Crash.main(null);
		}
		/*AddRecipe("Rock", "Empty", "Rock", 1, 1, true);
		AddRecipe("Rocks", "Empty", "Rock", 1, 1, true);
		AddRecipe("Tree", "Empty", "Stick", 1, 2, false);
		AddRecipe("Tree", "Axe", "Wood", 1, 1, true);
		AddRecipe("MahoganyTree", "Empty", "Stick", 1, 2, false);
		AddRecipe("MahoganyTree", "Axe", "MahoganyWood", 1, 1, true);
		AddRecipe("MapleTree", "Empty", "Stick", 1, 2, false);
		AddRecipe("MapleTree", "Axe", "MapleWood", 1, 1, true);
		AddRecipe("WalnutTree", "Empty", "Stick", 1, 2, false);
		AddRecipe("WalnutTree", "Axe", "WalnutWood", 1, 1, true);*/
	}
	
	public static boolean LoadRecipeList () {
		String recipes = ReadFromFile.readstuff("conf\\", "Recipelist.tool", "");
		if (recipes != null && recipes.length() != 0) {
			String[] split = recipes.split("\n");
			for (int i = 0; i < split.length; i++) {
				if (split[i].charAt(0) == '#') {
					continue;
				}
				String[] args = split[i].split(",");
				AddRecipe(args[0], args[1], args[2], args[3], args[4], Boolean.parseBoolean(args[5]));
			}
			return true;
		}
		return false;
	}
	
	public static boolean CheckRecipe (String block, String item) {
		for (int i = 0; i < Block.size(); i++) {
			if (Block.get(i).equalsIgnoreCase(block)) {
				if (Item.get(i).equalsIgnoreCase(item)) {
					String resultItem = ResultItem.get(i);
					if (resultItem.contains("|")) {
						String[] splitItems = resultItem.split("\\|");
						String[] splitAmount = ResultAmount.get(i).split("\\|");
						String[] splitTypes = ResultType.get(i).split("\\|");
						for (int j = 0; j < splitItems.length; j++) {
							Inventory.AddItem(splitItems[j], Byte.parseByte(splitAmount[j]), Byte.parseByte(splitTypes[j]));
						}
					} else {
						Inventory.AddItem(resultItem, Byte.parseByte(ResultAmount.get(i)), Byte.parseByte(ResultType.get(i)));
					}
					return DestroyBlock.get(i);
				}
			}
		}
		return false; //Your block could not be found.
	}
	
	public static void AddRecipe (String name, String usedItem, String resultItem, String resultAmount, String resultType, boolean destroyBlock) {
		Block.add(name);
		Item.add(usedItem);
		ResultItem.add(resultItem);
		ResultAmount.add(resultAmount);
		ResultType.add(resultType);
		DestroyBlock.add(destroyBlock);
	}
	
}
