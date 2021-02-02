/* 
   To change this license header, choose License Headers in Project Properties.
   To change this template file, choose Tools | Templates
   and open the template in the editor.
  */
package first_gui;

/*  
  
   @author zezo
  */
public class tree {
    
     enum color{Red,black};
    public class node{ 
    public int value; 
    public node left;
    public    node right;
    public   node parent;
    public    color c=color.Red;
        
        public node(){ 
            value= 0;
            left =null;
            right = null;
            parent=null;
        } 
    };
     
  
    node root;
    int length;
    
    public tree(){
    this.root=null;
    length= 0;
    
    }
   
  
public void inorderHelper(node  root)
{
    if (root ==  null)
        return;

    inorderHelper(root.left);
    if(root.c==color.Red){
        System.out.print("red ");}
    System.out.println( root.value);
    inorderHelper(root.right);
}



public  void rotateLeft(node Node, node pre, int Case){
if(pre.parent==null){

if(Node.left!=null){
pre.right=Node.left;
Node.left.parent=pre;
}
else{pre.right=null;}

Node.left=pre;
pre.parent=Node;
Node.parent=null;
root=Node;

}
else{
if(pre.parent.right==pre){
pre.parent.right=Node;
}
else{pre.parent.left=Node;}

if(Node.left!=null){
pre.right=Node.left;
Node.left.parent=pre;
}
else{pre.right=null;}

Node.parent=pre.parent;
Node.left=pre;
pre.parent=Node;
}

if(Case==3){recolor(pre,Node,Node.right);}

if(Case==4){
pre.c=color.Red;
Node.c=color.black;
}

if(Case==5){
Node.c=color.Red;
Node.left.c=color.black;
Node.right.c=color.black;
}

if(Case==6){
Node.c=color.black;
Node.left.c=color.Red;
}

}



 void rotateRight(node  Node, node  pre,int Case){

if(pre.parent== null){

if(Node.right!= null){
pre.right=Node.right;
Node.right.parent=pre;
}
else{pre.left= null;}
Node.right=pre;
pre.parent=Node;
pre.left= null;
Node.parent= null;
root=Node;
}

else{

if(pre.parent.right==pre){
pre.parent.right=Node;
}
else{pre.parent.left=Node;}

if(Node.right!= null){
pre.right=Node.right;
Node.right.parent=pre;
}
else{pre.left= null;}
Node.parent=pre.parent;
Node.right=pre;
pre.parent=Node;

}

if(Case==3){recolor(pre,Node,Node.right);}

if(Case==4){

pre.c=color.Red;
Node.c=color.black;

}

if(Case==5){
Node.c=color.Red;
Node.left.c=color.black;
Node.right.c=color.black;

}

if(Case==6){
Node.c=color.black;
Node.right.c=color.Red;

}

}

void recolor(node   left ,node   mid ,node   right){
 left.c=color.Red;
 mid.c=color.black;
 right.c=color.Red;
}


void Fix_up(node  newnode){

if(newnode.parent!= null){
    node  uncle = null;
    color uncleC;
    String uncleP="";
    String nodeP="";

if(newnode.parent.c == color.Red) {

  node  grandparent=newnode.parent.parent;

//get uncle position and color

if(grandparent.left==newnode.parent){
    uncleP="right";

  if(grandparent.right== null){
     uncleC=color.black;
}

else{
 uncle=grandparent.right;
  uncleC=uncle.c;
}

}

else{
uncleP="left";
if(grandparent.left== null){uncleC=color.black;}
else{
uncle=grandparent.left;
uncleC=uncle.c;
}
}
//get node position
if(newnode==newnode.parent.left){nodeP="left";}
else{nodeP="right";}

//case 1
if(uncleC== color.Red){

newnode.parent.c=color.black;
grandparent.c=color.Red;
uncle.c=color.black;

if(root==grandparent){
grandparent.c=color.black;
}
Fix_up(grandparent);
}
else if(uncleC==color.black){
//case 2 left

if(nodeP=="left"&&uncleP=="left"){

rotateRight(newnode,newnode.parent,2);
Fix_up(newnode.right);

}
//case 2 right

else if(nodeP=="right"&&uncleP=="right"){

rotateLeft(newnode,newnode.parent,2);
Fix_up(newnode.left);

}
//case 3 left right

if(nodeP=="left"&&uncleP=="right"){

rotateRight(newnode.parent,grandparent,3);

}
//case 3 right left

if(nodeP=="right"&&uncleP=="left"){

rotateLeft(newnode.parent,grandparent,3);

}

}

}
}
}

public void insertnode(int item){
node cur=root;
node  pre= null;

  while(cur!= null){
    pre=cur;
    if(item < cur.value){
    cur=cur.left;}

else{cur=cur.right;}
}
    node  newNode=new node();
    newNode.value=item;
    if(length== 0){
        root = newNode;
        root.c=color.black;
    }
    else if(item<pre.value){
          pre.left=newNode;
          newNode.parent=pre;

    }

else{
      pre.right=newNode;
      newNode.parent=pre;
    }

   Fix_up(newNode);

        length++;
}


public void Delete(int key){
node current=root;
int newValue= 0;

while(current!= null){

    if(current.value>key){
    current=current.left;
    } if(current.value<key){
    current=current.right;

    }if(current.value==key){
        break;
    }
}
node Replacement=current;

//check left child exist or not
if(Replacement.left!= null){
Replacement=Replacement.left;
//get Replacement
while(Replacement.right!= null){
Replacement=Replacement.right;

}
newValue=Replacement.value;
Delete(Replacement.value);
current.value=newValue;
}

else if(Replacement.left== null){
        //get Replacement position

if(Replacement.parent== null){
 if(Replacement.right!= null){
    root=Replacement.right;

 root.c=color.black;
 root.parent= null;

  length--;
 }
 else{
root= null;
length--;
 }
 }

else if(Replacement.parent.right==Replacement){

if(Replacement.c==color.black){Fix_Delete(Replacement);}
Replacement.parent.right= null;

length--;
}

else if(Replacement.parent.left==Replacement){
if(Replacement.c==color.black){Fix_Delete(Replacement);}
Replacement.parent.left= null;
length--;
}

}

}


void Fix_Delete(node   x){
    String nodeP;
    node  sipling;
    //case 5


 if(x.parent.left==x){
    nodeP="Left";
   sipling=x.parent.right;
}
else{
nodeP="Right";
sipling=x.parent.left;
}
//case 1
if(sipling.c==color.Red&&nodeP=="Left"){
rotateLeft(sipling,sipling.parent,4);
Fix_Delete(x);
}
else if(sipling.c==color.Red&&nodeP=="Right"){
    rotateRight(sipling,sipling.parent,4);
}
else if(sipling.c==color.black){
node  nephweR;
node  nephweL;
nephweR=sipling.right;
nephweL=sipling.left;
//case 4 treminated
if(nodeP=="Left"&&nephweR!= null){
if(nephweR.c==color.Red){
rotateLeft(sipling,sipling.parent,5);
}
}
else if(nodeP=="Right"&&nephweL!= null){
if(nephweL.c==color.Red){
rotateRight(sipling,sipling.parent,5);
}
}
//case 3
else if(nodeP=="Left"&&nephweL!= null){

    if(nephweL.c==color.Red){rotateRight(nephweL,sipling,6);
    }

    Fix_Delete(x);
}

else if(nodeP=="Right"&&nephweR!= null){

    if(nephweR.c==color.Red){rotateLeft(nephweR,sipling,6);}

    Fix_Delete(x);

}

//case 2
else if(nephweL== null&&nephweR== null){

sipling.c=color.Red;
sipling.parent.c=color.black;

}

else if(nephweL.c==color.black&&nephweR.c==color.black){

sipling.c=color.Red;
sipling.parent.c=color.black;
}
}

if(root.c==color.Red){
    root.c=color.black;
}


}

public  void Clear(node R){
this.root= null;
length=0;
}

}



  
    
    
    
