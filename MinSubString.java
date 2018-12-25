
// String input
// Map<Char, <count, locations>>
// int min
// int start

class MinSubString{
    public int findMinSubString(String input, List<Character> toFind){
        int total = toFind.size();
        if(total>input.length()){
            return -1;
        }
        
        Map<Character, Integer> lookupMap = makeLookupMap(toFind);
        int start = 0;
        int end = 0;
        int min = Integer.MAX;
        while(end<input.length() && start<input.length()){
            if(total>0){
                char e = input.charAt(end);
                if(lookupMap.containsKey(e)){
                    lookupMap.put(lookupMap.get(e)-1);
                    if(lookupMap.get(e)>=0){
                        total--;
                    }
                }
                end++;
            } else if(total==0){
                int l = end-start;
                min = Math.min(min, l);
                char s = input.charAt(start);
                start++;
                if(lookupMap.containsKey(s)){
                    lookupMap.put(lookupMap.get(e)+1);
                    if(lookupMap.get(e)>0){
                        total++;
                    }
                }
            }
        }
        
    }
}
