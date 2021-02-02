#include <iostream>
#include "RBT.h"

using namespace std;

int main()
{

RBT x;
x.insertnode(3);
x.insertnode(2);
x.insertnode(5);
x.insertnode(7);
x.insertnode(6);






x.inorderHelper(x.root);


    return 0;
}
