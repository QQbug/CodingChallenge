class CountAndSay {
    public String countAndSay(int n) {
        String cur = "1";
        for(int i=2; i<=n; i++){
            char curD = cur.charAt(0);
            int count = 0;
            StringBuilder sb = new StringBuilder();
            for(int j=0; j<cur.length(); j++){
                char nextD = cur.charAt(j);
                if(curD==nextD){
                    count++;
                } else {
                    sb.append(count);
                    sb.append(curD);
                    count = 1;
                    curD = nextD;
                }
            }
            sb.append(count);
            sb.append(curD);
            cur = sb.toString();
        }
        return cur;
    }
}
