class Solution {
    public int step=0;
    public void dfs(int i, int parent, int steps[], int lowSteps[], List<List<Integer>> adj,
    List<List<Integer>> ret){

        steps[i]=step;
        lowSteps[i]=step;


        
        for(int el:adj.get(i)){
            
            // if(steps[el]==0){
            //     step++;
            //     dfs(el,i,steps,lowSteps,adj,ret);
            // }
            // if(el!=parent)lowSteps[i]=Math.min(lowSteps[i],lowSteps[el]);

            if(steps[el]==0){
                step++;
                dfs(el,i,steps,lowSteps,adj,ret);
                lowSteps[i] = Math.min(lowSteps[i], lowSteps[el]);
            }
            else if(el!=parent)lowSteps[i] = Math.min(lowSteps[i], steps[el]);
               
        }

        if(parent==-1)return;
        if(parent!=-1&&adj.get(parent).size()==1){
            ret.add(List.of(Math.min(parent,adj.get(parent).get(0)),Math.max(parent,adj.get(parent).get(0))));
            
        }
        else if(lowSteps[i]<=steps[parent]){
            lowSteps[parent]=Math.min(lowSteps[parent],lowSteps[i]);
        }

        else  {
            ret.add(new ArrayList<>(List.of(parent,i)));
        }
    }
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {

        List<List<Integer>> adj= new ArrayList<>();
        for(int i=0;i <n; i++)adj.add(new ArrayList<>());

        for(int i=0; i<connections.size(); i++){
            int a=connections.get(i).get(0);
            int b=connections.get(i).get(1);
            adj.get(a).add(b);
            adj.get(b).add(a);
        } 

        int steps[]=new int[n];
        int lowSteps[]=new int[n];


        List<List<Integer>> ret = new ArrayList<>();
        step=1;

        for(int i=0; i<n; i++){
            if(steps[i]==0)dfs(i,-1,steps,lowSteps,adj,ret);
            
        }

        return ret;
    }
}