/**
 * =====================================================================
 *  DAY 3  -  MERGE SORT
 * =====================================================================
 *
 *  THE IDEA  (divide and conquer)
 *   1. Split the list in half.
 *   2. Sort each half (using merge sort! - this is recursion).
 *   3. MERGE the two sorted halves into one sorted list.
 *  A list with 0 or 1 items is already sorted - that's your base case.
 *
 *  MERGING two sorted halves  a[lo..mid]  and  a[mid+1..hi]:
 *   - Copy a[lo..hi] into a temporary array first:   temp[k] = arr.get(k);
 *   - Keep one "finger" at the start of each half (in temp).
 *   - Compare the two items under your fingers, write the smaller one back
 *     into the list with arr.set(k, ...), and move that finger forward.
 *   - When one half runs out, copy the rest of the other half.
 *
 *  SUGGESTED STRUCTURE (you write the bodies):
 *      public void sort(SortArray arr)                        -> make temp, call mergeSort on the whole list
 *      private void mergeSort(SortArray arr, Item[] temp, int lo, int hi)
 *      private void merge(SortArray arr, Item[] temp, int lo, int mid, int hi)
 *
 *  TOOLS
 *      Item[] temp = new Item[arr.size()];   a temporary array
 *      arr.get(k)                            read an item (free)
 *      arr.less(temp[i], temp[j])            compare two items you are holding
 *      arr.set(k, item)                      write an item into the list
 *
 *  ANALYSIS QUESTIONS
 *   1. Merge sort makes 0 swaps. Why? What does it do instead?
 *   2. Try n = 16, 32, 64 Random. Does the comparison count roughly fit n * log2(n)?
 *   3. Does merge sort care whether the data starts sorted, reversed or random?
 *   4. Is your merge sort stable? (Hint: when two items are EQUAL, which one goes first?)
 */
public class MergeSort implements SortAlgorithm {

    @Override
    public String getName() { return "Merge Sort"; }


    @Override
    public void sort(SortArray arr) {
        // TODO: write merge sort here (add helper methods below)
        Item[] tmp = new Item[arr.size()];
        mergeSort(arr,tmp,0,arr.size()-1);
    }

    private void mergeSort(SortArray arr, Item[] tmp, int lo, int hi) {
        if(hi<=lo) return;
        int mid = lo+(hi-lo)/2;
        mergeSort(arr,tmp,lo,mid);
        mergeSort(arr,tmp,mid+1,hi);
        merge(arr,tmp,lo,mid,hi);
    }
    private void merge(SortArray arr, Item[] tmp, int lo, int mid, int hi) {
        //copy both piles into tmp
        for(int z = lo; z<=hi;z++) {
            tmp[z] = arr.get(z);
        }
        int i = lo;
        int j = mid+1;
        for(int k = lo;k<=hi;k++) {
            if(i>mid) {
                arr.set(k,tmp[j++]);
            } else if (j>hi) {
                arr.set(k, tmp[i++]);
            }else if (arr.less(tmp[j],tmp[i])) {
                arr.set(k,tmp[j++]);
            } else {
                arr.set(k,tmp[i++]);
            }
        }
    }
}
