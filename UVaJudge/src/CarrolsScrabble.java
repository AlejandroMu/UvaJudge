import java.io.*;
import java.util.*;

public class CarrolsScrabble {
	static HashMap<String,Integer> values;
	static HashMap<String,ArrayList<String>> ways;
	static HashMap<String,ArrayList<String>> adyacentes;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
		load();
		HashMap<Integer, HashMap<String,String>> words=new HashMap<>();
		HashSet<String> mar=new HashSet<>();
		int dic=Integer.parseInt(br.readLine());
		for (int i = 0; i < dic; i++) {
			String word=br.readLine();
			int length=word.length();
			char[] cha=word.toCharArray();
			Arrays.sort(cha);
			String wordOr=new String(cha);
			HashMap<String, String> tmp=words.get(length);
			if(tmp==null) {
				tmp=new HashMap<>();
			}
			tmp.put(word, wordOr);
			words.put(length, tmp);
			mar.add(word);
			
		}
		int con=Integer.parseInt(br.readLine());
		for (int i = 0; i < con; i++) {
				String[] line=br.readLine().split(" ");
				if(line[0].length()==line[2].length()) {
					ArrayList<String> res=new ArrayList<>();
					boolean a=response(words.get(line[0].length()),line[0],line[2],mar,res);
					if(a) {
						int points=calculatePoints(res,words.get(line[0].length()));
						bw.write(line[0]+" TO "+line[2]+" "+res.size()+" "+points+"\n");
					}else {
						bw.write(line[0]+" TO "+line[2]+" IMPOSSIBLE\n");

					}
				}else {
					bw.write(line[0]+" TO "+line[2]+" IMPOSSIBLE\n");
				}
				
		}
		bw.close();
		br.close();
	}

	private static int calculatePoints(ArrayList<String> res, HashMap<String, String> hashMap) {
		int ret=0;
		for (int i = 1; i < res.size(); i++) {
			ret+=weight(hashMap.get(res.get(i)));
		}
		return ret;
	}
	public static int weight(String a) {
		int ret=0;
		if(values.containsKey(a)) {
			return values.get(a);
		}
		else {
			ret+=values.get(a.charAt(0)+"");
			String sub=a.substring(1);
			int res=weight(sub);
			values.put(sub, res);
			ret+=res;
			return ret;
		}
		
	}

	private static void load() {
		values=new HashMap<>();
		ways=new HashMap<>();
		adyacentes=new HashMap<>();
		values.put("a",1);
		values.put("e",1);
		values.put("i",1);
		values.put("o",1);
		values.put("u",1);
		values.put("n",1);
		values.put("r",1);
		values.put("t",1);
		values.put("s",1);
		values.put("l",1);
		
		values.put("d",2);
		values.put("g",2);
		
		values.put("b",3);
		values.put("c",3);
		values.put("m",3);
		values.put("p",3);
		
		values.put("f",4);
		values.put("h",4);
		values.put("v",4);
		values.put("w",4);
		values.put("y",4);
		
		values.put("k",5);
		
		values.put("j",8);
		values.put("x",8);
		
		values.put("q",10);
		values.put("z",10);

		
	}
	public static String key(String a,String b) {
		return a.compareTo(b)<0?a+"_"+b:b+"_"+a;
	}
	@SuppressWarnings("unchecked")
	private static boolean response(HashMap<String, String> words, String string, String string2,HashSet<String> mar,ArrayList<String> response) {
		String key=key(string, string2);
		if(ways.containsKey(key)) {
			response.addAll(ways.get(key));
			return true;
		}
		if(string.equals(string2)) {
			return true;
		}else if(mar.contains(string)) {
			mar.remove(string);
			int size=Integer.MAX_VALUE;
			ArrayList<String> tmp=new ArrayList<>();
			ArrayList<String> ady=getAdyacentes(string,words);
			boolean res=false;
			for (int i = 0; i < ady.size(); i++) {
				if(mar.contains(ady.get(i))) {
					ArrayList<String> tmp2=new ArrayList<>();
					boolean tmp1=response(words, ady.get(i), string2, (HashSet<String>) mar.clone(),tmp2);
					res=res||tmp1;
					if(tmp1) {
						tmp2.add(ady.get(i));
						if(tmp2.size()<size) {
							tmp=tmp2;
							size=tmp.size();
						}
						
					}
					
				}
			}
			
			if(res) {
				ways.put(key, tmp);
				response.addAll(tmp);
			}
			mar.add(string);
			
			return res;
		}else {
			return false;
		}
	}

	private static ArrayList<String> getAdyacentes(String string, HashMap<String, String> words) {
		ArrayList<String> ret=new ArrayList<>();
		if(adyacentes.containsKey(string)) {
			return adyacentes.get(string);
		}
		Iterator<String> keys=words.keySet().iterator();
		while(keys.hasNext()) {
			String tmp=keys.next();
			if(!tmp.equals(string)) {
				if(words.get(string).equals(words.get(tmp))){
					ret.add(tmp);
				}else{
					int d=0;
					for (int i = 0; i < string.length(); i++) {
						if(tmp.charAt(i)!=string.charAt(i)) {
							d++;
						}
						if(d>1) {
							break;
						}
						
					}
					if(d==1) {
						ret.add(tmp);
					}
				}
			}
		}
		adyacentes.put(string, ret);
		
		return ret;
	}
}