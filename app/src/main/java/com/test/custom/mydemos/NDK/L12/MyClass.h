//
// Created by Administrator on 2017/10/23.
//

#ifndef MYDEMOS_MYCLASS_H
#define MYDEMOS_MYCLASS_H

#endif //MYDEMOS_MYCLASS_H

class MyClass{

    public:
        MyClass();
        ~MyClass();

        void setAge(int age);
        int getAge();
        void setName(char *name);
        char * getName();
    private:
        int age;
        char *name;
};