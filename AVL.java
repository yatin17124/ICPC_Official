class Node{
	int val;
	int ht;
	Node left;
	Node right;
}

class AVL {
	Node root;
	AVL(){
		root=null;
	}
	
	Node insert(Node root,int val)
	{
	   Node nptr=new Node();
	   nptr.val=val;
	   nptr.left=null;
	   nptr.right=null;
	   nptr.ht=0;
	   if(root==null){
	       root=nptr;
	       return root;
	   }
	   if(val<=root.val){
	       root.left=insert(root.left,val);
	   }
	   else{
	       root.right=insert(root.right,val);
	   }
	   int lh=height(root.left);
	   int rh=height(root.right);
	   if(Math.abs(lh-rh)>1){
	       if(lh>rh){
	           if(height(root.left.right)>height(root.left.left))
	               root.left=RightRotate(root.left);
	           root=LeftRotate(root);
	       }
	       else{
	           if(height(root.right.left)>height(root.right.right))
	               root.right=LeftRotate(root.right);
	           root=RightRotate(root);
	       }
	   }
	   root.ht=height(root);
	   return root;
	}
	int height(Node roote){
	    if(roote==null) return -1;
	    int left;
	    if(roote.left==null) left=-1;
	    else left=roote.left.ht;
	    int right;
	    if(roote.right==null) right=-1;
	    else right=roote.right.ht;
	    if(left>right) return left+1;
	    else return right+1;
	}
	Node LeftRotate(Node prev){
	    Node newr=prev.left;
	    Node child=newr.right;
	    prev.left=child;
	    newr.right=prev;
	    prev.ht=height(prev);
	    newr.ht=height(newr);
	    return newr;
	}
	 Node RightRotate(Node prev){
	    Node newr=prev.right;
	    Node child=newr.left;
	    prev.right=child;
	    newr.left=prev;
	    prev.ht=height(prev);
	    newr.ht=height(newr);
	    return newr;
	}
	 void inorder(Node roote) {
		 if(roote!=null) {
			 inorder(roote.left);
			 System.out.print(roote.val+" ");
			 inorder(roote.right);
		 }
	 }
}