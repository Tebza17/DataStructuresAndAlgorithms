import java.util.*;
public class Tester 
{
	public static void main(String[] args) throws Exception
	{
            /*
            TODO: Write your code to test your implementation here.

            This file will be overwritten for marking purposes
            */
            
//            DoubleThreadedBST<Integer> i = new DoubleThreadedBST<Integer>();
//            System.out.println(i.insert(10));
//            System.out.println(i.insert(12));
//            System.out.println(i.insert(15));
//            System.out.println(i.insert(15));
//            System.out.println(i.insert(2));
//            System.out.println(i.insert(13));
//            System.out.println(i.insert(1));
//            System.out.println(i.insert(4));
//            System.out.println(i.getNumberOfNodes());
//            System.out.println(i.inorderReverse());
//            System.out.println(i.getHeight());
//            System.out.println(i.getRoot().data);
//            System.out.println();
//            System.out.println(i.preOrder());
//            DoubleThreadedDSW<Integer> t = new DoubleThreadedDSW<Integer>();
//            System.out.println(t.insert(10));
//            System.out.println(t.insert(12));
//            System.out.println(t.insert(15));
//            System.out.println(t.insert(15));
//            System.out.println(t.insert(2));
//            System.out.println(t.insert(13));
//            System.out.println(t.insert(1));
//            System.out.println(t.insert(4));
//            System.out.println(t.getRoot().data);
//            System.out.println(t.preOrder());
//            System.out.println(t.delete(13));
//            System.out.println(t.preOrder());
//            //System.out.println(t.preorderDetailed());
//            System.out.println(t.inorderReverse());
//            System.out.println(t.inorderReverseDetailed());
//            t.balance();
//            int [] array = new int[]{1,2,3,4,5,6,7,8,9};
//            String str = "";
//            int count=0;
//            if(array.length % 2 != 0){
//                for(int i=0;i<array.length;i++){
//                    count++;
//                    str += array[i];
//                    if(count == 2){
//                        str += "|";
//                        count =0;
//                    }else{
//                        if(i != array.length-1)
//                        str += ",";
//                    }
//                }
//                System.out.println(str);
//            }else{
//                for(int i=0;i<array.length;i++){
//                    count++;
//                    str += array[i];
//                    if(count == 2){
//                        str += "|";
//                        count =0;
//                    }else{
//                        if(i != array.length-1)
//                        str += ",";
//                    }
//                }
//                System.out.println(str);
//            }
              System.out.println("2017/12/01".substring(0,4));
              int value = 2015 - Integer.parseInt("2017/12/01".substring(0,4));
              System.out.println(value);
              List<Integer> simpleList = new ArrayList<>();
              List<Integer> listOfpoliciesWithPolicyNumber1 = new ArrayList<>();
              List<Integer> listOfpoliciesWithPolicyNumber2 = new ArrayList<>();
              List<Integer> listOfpoliciesWithPolicyNumber3 = new ArrayList<>();
              List<Integer> listOfpoliciesWithPolicyNumber4 = new ArrayList<>();
              
              for(int p=0; p<5; p++){
                  simpleList.add(p);
                  listOfpoliciesWithPolicyNumber1.add(p);
                  listOfpoliciesWithPolicyNumber2.add(p+1);
                  listOfpoliciesWithPolicyNumber3.add(p+2);
                  listOfpoliciesWithPolicyNumber4.add(p+3);
              }
              System.out.println(Collections.max(simpleList));
              Map<Integer,List<Integer>> groupedStagedPolicies = new HashMap<>();
              groupedStagedPolicies.put(1, listOfpoliciesWithPolicyNumber1);
              groupedStagedPolicies.get(1).add(500);
              groupedStagedPolicies.put(2, listOfpoliciesWithPolicyNumber2);
              groupedStagedPolicies.put(3, listOfpoliciesWithPolicyNumber3);
              groupedStagedPolicies.put(4, listOfpoliciesWithPolicyNumber4);
              for(Map.Entry<Integer,List<Integer>> listOfStagedPolicies :groupedStagedPolicies.entrySet()){
                  System.out.println("List of Policies with policy number: "+listOfStagedPolicies.getKey());
                  for(Integer policie: listOfStagedPolicies.getValue()){
                      System.out.println("Do something with this = " + policie + " (id value)");
                  }
                  //System.out.println(listOfStagedPolicies.getValue().get(0));
                  
              }
              
              
                
//            DoubleThreadedBST<Character> p = new DoubleThreadedBST<Character>();
//            System.out.println(p.insert('B'));
//            System.out.println(p.insert('D'));
//            System.out.println(p.insert('E'));
//            System.out.println(p.insert('A'));
//            System.out.println(p.insert('C'));
//            System.out.println(p.insert('F'));
//            System.out.println(p.insert('H'));
//            System.out.println(p.insert('X'));
//            System.out.println(p.getNumberOfNodes());
//            System.out.println(p.inorderReverse());
//            System.out.println(p.inorderReverseDetailed());
//            System.out.println(p.preOrder());
//            System.out.println(p.preorderDetailed());
//            System.out.println(p.getHeight());
//            System.out.println(p.getRoot().data);
//            System.out.println(p.clone().getNumberOfNodes());
////            System.out.println();
////            System.out.println(p.preOrder());
//            System.out.println();
//            System.out.println();
//            System.out.println();
//            DoubleThreadedBST<Integer> BST = new DoubleThreadedBST<Integer>();
//
//            BST.insert(1);
//            BST.insert(2);
//            BST.insert(3);
//            BST.insert(5);
//            BST.insert(4);
//            System.out.println(BST.inorderReverse());
	}
}
