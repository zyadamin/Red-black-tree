#ifndef RBT_H
#define RBT_H
#include <iostream>
using namespace std;

enum color{Red,black};

struct node{
int value;
node* left;
node* right;
node* parent;
enum color c;



node(){
value=0;
this->right=0;
this->left=0;
this->c=Red;
this->parent=0;
}


};


class RBT
{
     private:
    int length;

    public:
      node * root;

    RBT(){

     this->root=0;
     length=0;

}


void inorderHelper(node *root)
{
    if (root == 0)
        return;

    inorderHelper(root->left);
    if(root->c==Red){cout<<"red ";}
    cout << root->value <<endl;;
    inorderHelper(root->right);
}



void rotateLeft(node *Node, node *pre, int Case){
if(pre->parent==0){

if(Node->left!=0){
pre->right=Node->left;
Node->left->parent=pre;
}
else{pre->right=0;}

Node->left=pre;
pre->parent=Node;
Node->parent=0;
root=Node;

}
else{
if(pre->parent->right==pre){
pre->parent->right=Node;
}
else{pre->parent->left=Node;}

if(Node->left!=0){
pre->right=Node->left;
Node->left->parent=pre;
}
else{pre->right=0;}

Node->parent=pre->parent;
Node->left=pre;
pre->parent=Node;
}

if(Case==3){recolor(pre,Node,Node->right);}

if(Case==4){
pre->c=Red;
Node->c=black;
}

if(Case==5){
Node->c=Red;
Node->left->c=black;
Node->right->c=black;
}

if(Case==6){
Node->c=black;
Node->left->c=Red;
}

}



void rotateRight(node *Node, node *pre,int Case){

if(pre->parent==0){

if(Node->right!=0){
pre->right=Node->right;
Node->right->parent=pre;
}
else{pre->left=0;}
Node->right=pre;
pre->parent=Node;
pre->left=0;
Node->parent=0;
root=Node;
}

else{

if(pre->parent->right==pre){
pre->parent->right=Node;
}
else{pre->parent->left=Node;}

if(Node->right!=0){
pre->right=Node->right;
Node->right->parent=pre;
}
else{pre->left=0;}
Node->parent=pre->parent;
Node->right=pre;
pre->parent=Node;

}

if(Case==3){recolor(pre,Node,Node->right);}

if(Case==4){

pre->c=Red;
Node->c=black;

}

if(Case==5){
Node->c=Red;
Node->left->c=black;
Node->right->c=black;

}

if(Case==6){
Node->c=black;
Node->right->c=Red;

}

}

void recolor(node * left ,node * mid ,node * right){
 left->c=Red;
 mid->c=black;
 right->c=Red;
}


void Fix_up(node *newnode){

if(newnode->parent!=0){
    node *uncle;
    int uncleC;
    string uncleP="";
    string nodeP="";

if(newnode->parent->c == Red) {

  node* grandparent=newnode->parent->parent;

//get uncle position and color

if(grandparent->left==newnode->parent){
    uncleP="right";

  if(grandparent->right==0){
     uncleC=1;
}

else{
 uncle=grandparent->right;
  uncleC=uncle->c;
}

}

else{
uncleP="left";
if(grandparent->left==0){uncleC=1;}
else{
uncle=grandparent->left;
uncleC=uncle->c;
}
}
//get node position
if(newnode==newnode->parent->left){nodeP="left";}
else{nodeP="right";}

//case 1
if(uncleC==0){

newnode->parent->c=black;
grandparent->c=Red;
uncle->c=black;

if(root==grandparent){
grandparent->c=black;
}
Fix_up(grandparent);
}
else if(uncleC==1){
//case 2 left

if(nodeP=="left"&&uncleP=="left"){

rotateRight(newnode,newnode->parent,2);
Fix_up(newnode->right);

}
//case 2 right

else if(nodeP=="right"&&uncleP=="right"){

rotateLeft(newnode,newnode->parent,2);
Fix_up(newnode->left);

}
//case 3 left right

if(nodeP=="left"&&uncleP=="right"){

rotateRight(newnode->parent,grandparent,3);

}
//case 3 right left

if(nodeP=="right"&&uncleP=="left"){

rotateLeft(newnode->parent,grandparent,3);

}

}

}
}
}

void insertnode(int item){
node*cur=root;
node *pre=0;

  while(cur!=0){
    pre=cur;
    if(item < cur->value){
    cur=cur->left;}

else{cur=cur->right;}
}
    node *newNode=new node();
    newNode->value=item;
    if(length==0){
        root = newNode;
        root->c=black;
    }
    else if(item<pre->value){
          pre->left=newNode;
          newNode->parent=pre;

    }

else{
      pre->right=newNode;
      newNode->parent=pre;
    }

   Fix_up(newNode);

        length++;
}


void Delete(int key){
node*current=root;
int newValue=0;

while(current!=0){

    if(current->value>key){
    current=current->left;
    } if(current->value<key){
    current=current->right;

    }if(current->value==key){
        break;
    }
}
node*Replacement=current;

//check left child exist or not
if(Replacement->left!=0){
Replacement=Replacement->left;
//get Replacement
while(Replacement->right!=0){
Replacement=Replacement->right;

}
newValue=Replacement->value;
Delete(Replacement->value);
current->value=newValue;
}

else if(Replacement->left==0){
        //get Replacement position

if(Replacement->parent==0){
 if(Replacement->right!=0){
    root=Replacement->right;

 root->c=black;
 root->parent=0;

  delete Replacement;
  length--;
 }
 else{
delete Replacement;
root=0;
length--;
 }
 }

else if(Replacement->parent->right==Replacement){

if(Replacement->c==black){Fix_Delete(Replacement);}
Replacement->parent->right=0;

delete Replacement;
length--;
}

else if(Replacement->parent->left==Replacement){
if(Replacement->c==black){Fix_Delete(Replacement);}
Replacement->parent->left=0;

delete Replacement;
length--;
}

}

}


void Fix_Delete(node * x){
    string nodeP;
    node* sipling;
    //case 5


 if(x->parent->left==x){
    nodeP="Left";
   sipling=x->parent->right;
}
else{
nodeP="Right";
sipling=x->parent->left;
}
//case 1
if(sipling->c==Red&&nodeP=="Left"){
rotateLeft(sipling,sipling->parent,4);
Fix_Delete(x);
}
else if(sipling->c==Red&&nodeP=="Right"){
    rotateRight(sipling,sipling->parent,4);
}
else if(sipling->c==black){
node* nephweR;
node* nephweL;
nephweR=sipling->right;
nephweL=sipling->left;
//case 4 treminated
if(nodeP=="Left"&&nephweR!=0){
if(nephweR->c==Red){
rotateLeft(sipling,sipling->parent,5);
}
}
else if(nodeP=="Right"&&nephweL!=0){
if(nephweL->c==Red){
rotateRight(sipling,sipling->parent,5);
}
}
//case 3
else if(nodeP=="Left"&&nephweL!=0){

    if(nephweL->c==Red){rotateRight(nephweL,sipling,6);
    }

    Fix_Delete(x);
}

else if(nodeP=="Right"&&nephweR!=0){

    if(nephweR->c==Red){rotateLeft(nephweR,sipling,6);}

    Fix_Delete(x);

}

//case 2
else if(nephweL==0&&nephweR==0){

sipling->c=Red;
sipling->parent->c=black;

}

else if(nephweL->c==black&&nephweR->c==black){

sipling->c=Red;
sipling->parent->c=black;
}
}

if(root->c==Red){
    root->c=black;
}


}

void Clear(node*R){

if(R!=0){
Clear(R->left);
Clear(R->right);
delete R;
this->root=0;}

}





};

#endif // RBT_H
