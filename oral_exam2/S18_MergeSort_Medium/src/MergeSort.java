public class MergeSort {

    private int[] data;

    public MergeSort(int[] d){
        data = d;
    }

    public int[] merge(int[] d){
        if(d.length == 1){
            return d;
        }
        int[] data1 = new int[d.length/2];
        int[] data2 = new int[d.length - (d.length/2)];
        for(int i = 0; i < d.length/2; i++){

        }
        return compare(merge(data1), merge(data2));
    }

    public int[] getData(){
        return data;
    }

    public int[] compare(int[] d1, int[] d2){
        int[] newData = new int[d1.length + d2.length];
        if(d1[d1.length-1] <= d2[0]){
            for(int i = 0; i < d1.length; i++){
                newData[i] = d1[i];
            }
            for(int i = d1.length; i < newData.length; i++){
                newData[i] = d2[i];
            }
        } else {
            for(int i = 0; i < d2.length; i++){
                newData[i] = d2[i];
            }
            for(int i = d2.length; i < newData.length; i++){
                newData[i] = d1[i];
            }
        }
        return newData;
    }
}
