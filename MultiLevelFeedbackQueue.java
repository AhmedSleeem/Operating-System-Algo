import java.io.*;
import java.util.*;

public class MultiLevelFeedbackQueue {

    public static void main(String[] args) throws Exception {
        Scanner in= new Scanner(System.in);
        try(PrintWriter or = new PrintWriter(System.out)) {

            or.println("please enter the number of levels");
            or.flush();
            int nunberoflevels=in.nextInt();
            ArrayDeque<Process>[] deques = new ArrayDeque[nunberoflevels];

            for (int i = 0; i < nunberoflevels; i++) deques[i] = new ArrayDeque();
            or.println("Please enter the number of process");
            or.flush();
            int nofProcess=in.nextInt();


            Process[] processes= new Process[nofProcess];
            or.println("enter data in format { arrivaltime      bursttime }");
            or.flush();


            for (int i = 0; i < nofProcess; i++) processes[i]= new Process(in.nextInt(),in.nextInt(),i+1);


            Arrays.sort(processes, new Comparator<Process>() {
                @Override
                public int compare(Process o1, Process o2) {
                    if (o1.arrivalTime==o2.arrivalTime)return o1.burstTime-o2.burstTime;
                    return o1.arrivalTime-o2.arrivalTime;
                }
            });


            //for storing answer
            StringBuilder ans= new StringBuilder();
            int y=nofProcess;


            for (int i = 0; y>0; i++) {
                Process process=null;
                int level=-1;
                for (int k = 0; k <nofProcess ; ++k) {
                    if (processes[k].arrivalTime==i){
                        deques[0].addLast(processes[k]);
                    }
                }
                for (int k = 0; k < deques.length; k++) {
                    if (deques[k].size()>0){
                         process = deques[k].pollFirst();
                         level=k;
                         break;
                    }
                }
                if (level!=-1){
                    Process cur=null;
                    if (process.burstTime-1==0){
                        ans.append(process.id+" ");
                        --y;
                    }else{
                        ans.append(process.id+" ");
                        cur= new Process(-1,process.burstTime-1,process.id);
//                        boolean f=false;
//                        for (int k = 0; k <nofProcess ; ++k) {
//                            if (processes[k].arrivalTime==i+1){
//                                f=true;
//                                break;
//                            }
//                        }

                        if (level+1>=deques.length){
                            deques[deques.length-1].addLast(cur);
                        }else {
//                            if (f) deques[level+1].addLast(cur);
//                            else
                           deques[level+1].addLast(cur);
                        }
                    }

                }
            }
            or.println(ans);



        }

    }

    static class Scanner {

        StringTokenizer st;
        BufferedReader br;

        public Scanner(InputStream s) {
            br = new BufferedReader(new InputStreamReader(s));
        }

        public String next() throws IOException {
            while (st == null || !st.hasMoreTokens()) {
                st = new StringTokenizer(br.readLine());
            }
            return st.nextToken();
        }

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }

        public long nextLong() throws IOException {
            return Long.parseLong(next());
        }

        public String nextLine() throws IOException {
            return br.readLine();
        }

        public double nextDouble() throws IOException {
            String x = next();
            StringBuilder sb = new StringBuilder("0");
            double res = 0, f = 1;
            boolean dec = false, neg = false;
            int start = 0;
            if (x.charAt(0) == '-') {
                neg = true;
                start++;
            }
            for (int i = start; i < x.length(); i++) {
                if (x.charAt(i) == '.') {
                    res = Long.parseLong(sb.toString());
                    sb = new StringBuilder("0");
                    dec = true;
                } else {
                    sb.append(x.charAt(i));
                    if (dec) {
                        f *= 10;
                    }
                }
            }
            res += Long.parseLong(sb.toString()) / f;
            return res * (neg ? -1 : 1);
        }

        public boolean ready() throws IOException {
            return br.ready();
        }

    }
static class Process{
        int arrivalTime,burstTime,id;

    public Process(int arrivalTime, int burstTime,int id) {
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.id=id;
    }
}
}
