package Game;

public class BlockInfo {
	
	//Get info from a block, returns all info.
	public static String[] GetBlockInfo (int block) {
		for (int i = 0; i < World2.blockinfo.length; i++) {
			String[] split = World2.blockinfo[i].split(",");
			if (split[0].equals(String.valueOf(block))) {
				return split;
			}
		}
		
		return null;
	}
	
	//Assign info to a block
	public static void SetBlockInfo (int block, String[] info) {
		String[] copy = World2.blockinfo.clone();
		String[] newinfo = new String[copy.length+1];
		
		for (int i = 0; i < copy.length; i++) {
			newinfo[i] = copy[i];
		}
		
		newinfo[newinfo.length-1] = block + ",";
		
		for (byte i = 0; i < info.length; i++) {
			newinfo[newinfo.length-1] = newinfo[newinfo.length-1] + info[i] + ",";
		}
		
		World2.blockinfo = newinfo;
	}
	
	public static void ReplaceBlockInfo (int block, String identifier, String info) {
		String[] split = new String[0];
		int number = 0;
		for (int i = 0; i < World2.blockinfo.length; i++) {
			String[] derpsplit = World2.blockinfo[i].split(",");
			if (derpsplit[0].equals(String.valueOf(block))) {
				split = derpsplit;
				number = i;
				break;
			}
		}
		
		String newinfo = "";
		
		for (byte i = 0; i < split.length; i++) {
			if (split[i].equals(identifier)) {
				split[i] = info;
			}
			newinfo = newinfo + split[i] + ",";
		}
		
		World2.blockinfo[number] = newinfo;
	}
	
	public static void RemoveAllInfo (int block) {
		boolean foundnumber = false;
		if (World2.blockinfo.length > 0) {
			String[] blockinfo_ = World2.blockinfo;
			World2.blockinfo = new String[World2.blockinfo.length-1];
			
			for (int i = 0; i < blockinfo_.length; i++) {
				String[] derpsplit = blockinfo_[i].split(",");
				if (derpsplit[0].equals(String.valueOf(block))) {
					for (int j = 0; j < blockinfo_.length; j++) {
						if (i != block) {
							if (foundnumber == false) {
								World2.blockinfo[i] = blockinfo_[i];
							}
							else {
								World2.blockinfo[i-1] = blockinfo_[i];
							}
						}
						else {
							foundnumber = true;
						}
					}
					return;
				}
			}
		}
	}
	
}
