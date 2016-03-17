import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Scanner;
import java.io.*;
import javax.swing.filechooser.*;

public class IntArrClass{
    private int[] numArray;
    private int size;
    
    public IntArrClass(long x){
      if(x == 0){
        this.numArray = new int[2];
        this.size = 1;
        return;
      }
      
      int numSize = 0 ;
      long number = x;
      if(x < 0){
        this.size = 1;
        this.numArray = new int[2];
      }
      
      while(x > 0){
        x = x/10;
        ++numSize;
      }
      
      this.numArray = new int[numSize];
      this.size = numSize;
      
      for(int i = 0; i < numSize; ++i){
        numArray[i] = (int) (number % 10);
        number = number/10;
      }
    }
    
    public void printNumber(){
      if(this.size == 0){
        System.out.print(0);
        System.out.println();
        return;
      }
      for(int i = this.size-1; i >= 0; --i){
        System.out.print(numArray[i]);
      }
      System.out.println();
    }
    
    public int getSize(){
      return this.size;
    }
    
    public void grow(){
      int[] newArray = new int[numArray.length*2];
      System.arraycopy(numArray, 0, newArray, 0, size);
      numArray = newArray;
    }
    
    public boolean isGreaterThan(IntArrClass y){
      if(this.size > y.getSize()){
        return true;
      }
      else if(this.size == y.getSize()){
        for(int i = this.getSize() - 1; i >= 0; --i){
          if(this.numArray[i] > y.numArray[i]){
            return true;
          }
          else if(this.numArray[i] < y.numArray[i]){
            return false;
          }
        }
      }
      
      return false;
    }
    
    public boolean isLessThan(IntArrClass y){
      if(this.size < y.getSize()){
        return true;
      }
      else if(this.size == y.getSize()){
        for(int i = this.getSize() - 1; i >= 0; --i){
          if(this.numArray[i] < y.numArray[i]){
            return true;
          }
          else if(this.numArray[i] > y.numArray[i]){
            return false;
          }
        }
      }
      
      return false;
    }
    
    public boolean isEqualTo(IntArrClass y){
      if(this.size != y.getSize()){
        return false;
      }
      else if(this.size == y.getSize()){
        for(int i = this.getSize() - 1; i >= 0; --i){
          if(this.numArray[i] != y.numArray[i]){
            return false;
          }
        }
      }
      
      return true;
    }
    
    public void add(IntArrClass y){
      
      while(y.getSize() >= this.numArray.length){
        this.grow();
      }
      
      int i = 0;
      
      for(i = 0; i < this.getSize(); ++i){
        if(i >= y.getSize()){
          if(this.numArray[i] >= 10){
            if((i+1) >= this.getSize()){
              if((i+1) >= this.numArray.length){
                this.grow();
              }
              ++this.size;
            }
            this.numArray[i+1] += this.numArray[i]/10;
            this.numArray[i] = this.numArray[i]%10;
          }
        }
        else{
          this.numArray[i] += y.numArray[i];
          
          if(this.numArray[i] >= 10){
            if((i+1) >= this.getSize()){
              if((i+1) >= this.numArray.length){
                this.grow();
              }
              ++this.size;
            }
            this.numArray[i+1] += this.numArray[i]/10;
            this.numArray[i] = this.numArray[i]%10;
          }
        }
      }
      
      if(y.getSize() > i){
        
        for(int j = this.getSize(); j < y.getSize(); ++j){
          this.numArray[j] = y.numArray[j];
          ++this.size;
        }
        
      }
      
      
      
    }
    
    public void subtract(IntArrClass y){
      
      if(this.isGreaterThan(y)){
        int i = 0;
      
        for(i = 0; i < this.getSize(); ++i){
          
          if(i >= y.numArray.length){
            y.grow();
          }
          
          if(this.numArray[i] < y.numArray[i]){
            --this.numArray[i+1];
            this.numArray[i] = (10+this.numArray[i]) - y.numArray[i];
          }
          else{
            this.numArray[i] = this.numArray[i] - y.numArray[i];
          }
        }
      
      }
      else{
        if(this.isLessThan(y)){
          int[] zeroArr = new int[this.getSize()];
          this.size = 0;
          this.numArray = zeroArr;
        }
        
        return;
      }
      
      
      int newSize = 0;
      for(int j = this.getSize()-1; j >= 0; --j){
        if(newSize >= 1 && this.numArray[j] == 0){
          ++newSize;
        }
        if(this.numArray[j] != 0){
          ++newSize;
        }
      }
      
      if(newSize == 0){
        this.size = 1;
      }
      else{
        this.size = newSize;
      }
    }
    
    public void multiply(IntArrClass y){
      
      IntArrClass[] subresults;
      
      if(this.isGreaterThan(y) || this.isEqualTo(y)){
        
        subresults = new IntArrClass[this.getSize()];
        
        for(int i = 0; i < subresults.length; ++i){
          
          subresults[i] = new IntArrClass(0);
          subresults[i].numArray = new int[this.getSize()*3];
          subresults[i].size = 0;
          
        }
      
        for(int i = 0; i < this.getSize(); ++i){
        
          for(int j = 0; j < y.getSize(); ++j){
          
            subresults[i].numArray[j] += this.numArray[i] * y.numArray[j];
            ++subresults[i].size;
            
            if(subresults[i].numArray[j] >= 10){
              subresults[i].numArray[j+1] += subresults[i].numArray[j]/10;
              subresults[i].numArray[j] = subresults[i].numArray[j]%10;
              ++subresults[i].size;
            }
               
          }
          
        }
        
        
        if(subresults.length > 1){
          
          for(int i = 1; i < subresults.length; ++i){
            
            for(int j = subresults.length + i; j >= i; --j){
             
              subresults[i].numArray[j] = subresults[i].numArray[j-i];
              subresults[i].numArray[j-i] = 0;
              
            }
            
            subresults[i].size += i;
            int newSize = 0;
            for(int k = subresults[i].size -1; k >= 0; --k){
              if(newSize >= 1 && subresults[i].numArray[k] == 0){
                ++newSize;
              }
              if(subresults[i].numArray[k] != 0){
                ++newSize;
              }
            }
            
            if(newSize == 0){
            
              subresults[i].size = 1;
            
            }
            else{
              subresults[i].size = newSize;
            }
          }
        }
        
        for(int i = 0; i < subresults.length - 1; ++i){
          
          subresults[0].add(subresults[i+1]);
          
        }
        
        this.numArray = subresults[0].numArray;
        this.size = subresults[0].size;
        
      }
      else{
        
        IntArrClass temp = y;
        temp.multiply(this);
        this.numArray = temp.numArray;
        this.size = temp.size;
          
      }
        
    }
   
    public void divide(IntArrClass y){
      
      if(y.isGreaterThan(this)){
        this.numArray = new int[this.getSize()];
        this.size = 1;
        return;
      }
      if(y.isEqualTo(this)){
        this.numArray = new int[this.getSize()];
        this.numArray[0] = 1;
        this.size = 1;
        return;
      }
      
      IntArrClass answer = new IntArrClass(0);
      IntArrClass increment = new IntArrClass(1);
      IntArrClass increment9 = new IntArrClass(1000000000);
      IntArrClass increment8 = new IntArrClass(100000000);
      IntArrClass increment7 = new IntArrClass(10000000);
      IntArrClass increment6 = new IntArrClass(1000000);
      IntArrClass increment5 = new IntArrClass(100000);
      IntArrClass increment4 = new IntArrClass(10000);
      IntArrClass increment3 = new IntArrClass(1000);
      IntArrClass increment2 = new IntArrClass(100);
      IntArrClass increment1 = new IntArrClass(10);
      IntArrClass tempY = new IntArrClass(0);
      tempY.numArray = y.numArray;
      tempY.size = y.size;
      IntArrClass tempInc = new IntArrClass(0);
      tempInc.numArray = y.numArray;
      tempInc.size = y.size;
      IntArrClass tempInc1000000000 = new IntArrClass(0);
      tempInc1000000000.numArray = y.numArray;
      tempInc1000000000.size = y.size;
      tempInc1000000000.multiply(new IntArrClass(1000000000));
      IntArrClass tempInc100000000 = new IntArrClass(0);
      tempInc100000000.numArray = y.numArray;
      tempInc100000000.size = y.size;
      tempInc100000000.multiply(new IntArrClass(100000000));
      IntArrClass tempInc10000000 = new IntArrClass(0);
      tempInc10000000.numArray = y.numArray;
      tempInc10000000.size = y.size;
      tempInc10000000.multiply(new IntArrClass(10000000));
      IntArrClass tempInc1000000 = new IntArrClass(0);
      tempInc1000000.numArray = y.numArray;
      tempInc1000000.size = y.size;
      tempInc1000000.multiply(new IntArrClass(1000000));
      IntArrClass tempInc100000 = new IntArrClass(0);
      tempInc100000.numArray = y.numArray;
      tempInc100000.size = y.size;
      tempInc100000.multiply(new IntArrClass(100000));
      IntArrClass tempInc10000 = new IntArrClass(0);
      tempInc10000.numArray = y.numArray;
      tempInc10000.size = y.size;
      tempInc10000.multiply(new IntArrClass(10000));
      IntArrClass tempInc1000 = new IntArrClass(0);
      tempInc1000.numArray = y.numArray;
      tempInc1000.size = y.size;
      tempInc1000.multiply(new IntArrClass(1000));
      IntArrClass tempInc100 = new IntArrClass(0);
      tempInc100.numArray = y.numArray;
      tempInc100.size = y.size;
      tempInc100.multiply(new IntArrClass(100));
      IntArrClass tempInc10 = new IntArrClass(0);
      tempInc10.numArray = y.numArray;
      tempInc10.size = y.size;
      tempInc10.multiply(new IntArrClass(10));
      
      while(tempY.isLessThan(this)){
        tempY.add(tempInc1000000000);
        if(tempY.isGreaterThan(this)){
          tempY.subtract(tempInc1000000000);
        }
        else{
          answer.add(increment9);
        }
        tempY.add(tempInc100000000);
        if(tempY.isGreaterThan(this)){
          tempY.subtract(tempInc100000000);
        }
        else{
          answer.add(increment8);
        }
        tempY.add(tempInc10000000);
        if(tempY.isGreaterThan(this)){
          tempY.subtract(tempInc10000000);
        }
        else{
          answer.add(increment7);
        }
        tempY.add(tempInc1000000);
        if(tempY.isGreaterThan(this)){
          tempY.subtract(tempInc1000000);
        }
        else{
          answer.add(increment6);
        }
        tempY.add(tempInc100000);
        if(tempY.isGreaterThan(this)){
          tempY.subtract(tempInc100000);
        }
        else{
          answer.add(increment5);
        }
        tempY.add(tempInc10000);
        if(tempY.isGreaterThan(this)){
          tempY.subtract(tempInc10000);
        }
        else{
          answer.add(increment4);
        }
        tempY.add(tempInc1000);
        if(tempY.isGreaterThan(this)){
          tempY.subtract(tempInc1000);
        }
        else{
          answer.add(increment3);
        }
        tempY.add(tempInc100);
        if(tempY.isGreaterThan(this)){
          tempY.subtract(tempInc100);
        }
        else{
          answer.add(increment2);
        }
        tempY.add(tempInc10);
        if(tempY.isGreaterThan(this)){
          tempY.subtract(tempInc10);
        }
        else{
          answer.add(increment1);
        }
        tempY.add(tempInc);
        answer.add(increment);
      }
      
      this.numArray = answer.numArray;
      this.size = answer.size;
      
    }
      
    public IntArrClass mod(IntArrClass y){
      
      IntArrClass tempThis = new IntArrClass(1);
      tempThis.numArray = this.numArray;
      tempThis.size = this.size;
      
      IntArrClass zeroInt = new IntArrClass(0);
      
      if(y.isGreaterThan(this)){
        return zeroInt;
      }
      if(y.isEqualTo(this)){
        return zeroInt;
      }
      
      IntArrClass answer = new IntArrClass(0);
      IntArrClass increment = new IntArrClass(1);
      IntArrClass increment9 = new IntArrClass(1000000000);
      IntArrClass increment8 = new IntArrClass(100000000);
      IntArrClass increment7 = new IntArrClass(10000000);
      IntArrClass increment6 = new IntArrClass(1000000);
      IntArrClass increment5 = new IntArrClass(100000);
      IntArrClass increment4 = new IntArrClass(10000);
      IntArrClass increment3 = new IntArrClass(1000);
      IntArrClass increment2 = new IntArrClass(100);
      IntArrClass increment1 = new IntArrClass(10);
      IntArrClass tempY = new IntArrClass(0);
      tempY.numArray = y.numArray;
      tempY.size = y.size;
      IntArrClass tempInc = new IntArrClass(0);
      tempInc.numArray = y.numArray;
      tempInc.size = y.size;
      IntArrClass tempInc1000000000 = new IntArrClass(0);
      tempInc1000000000.numArray = y.numArray;
      tempInc1000000000.size = y.size;
      tempInc1000000000.multiply(new IntArrClass(1000000000));
      IntArrClass tempInc100000000 = new IntArrClass(0);
      tempInc100000000.numArray = y.numArray;
      tempInc100000000.size = y.size;
      tempInc100000000.multiply(new IntArrClass(100000000));
      IntArrClass tempInc10000000 = new IntArrClass(0);
      tempInc10000000.numArray = y.numArray;
      tempInc10000000.size = y.size;
      tempInc10000000.multiply(new IntArrClass(10000000));
      IntArrClass tempInc1000000 = new IntArrClass(0);
      tempInc1000000.numArray = y.numArray;
      tempInc1000000.size = y.size;
      tempInc1000000.multiply(new IntArrClass(1000000));
      IntArrClass tempInc100000 = new IntArrClass(0);
      tempInc100000.numArray = y.numArray;
      tempInc100000.size = y.size;
      tempInc100000.multiply(new IntArrClass(100000));
      IntArrClass tempInc10000 = new IntArrClass(0);
      tempInc10000.numArray = y.numArray;
      tempInc10000.size = y.size;
      tempInc10000.multiply(new IntArrClass(10000));
      IntArrClass tempInc1000 = new IntArrClass(0);
      tempInc1000.numArray = y.numArray;
      tempInc1000.size = y.size;
      tempInc1000.multiply(new IntArrClass(1000));
      IntArrClass tempInc100 = new IntArrClass(0);
      tempInc100.numArray = y.numArray;
      tempInc100.size = y.size;
      tempInc100.multiply(new IntArrClass(100));
      IntArrClass tempInc10 = new IntArrClass(0);
      tempInc10.numArray = y.numArray;
      tempInc10.size = y.size;
      tempInc10.multiply(new IntArrClass(10));
      
      while(tempY.isLessThan(this)){
        tempY.add(tempInc1000000000);
        if(tempY.isGreaterThan(this)){
          tempY.subtract(tempInc1000000000);
        }
        else{
          answer.add(increment9);
        }
        tempY.add(tempInc100000000);
        if(tempY.isGreaterThan(this)){
          tempY.subtract(tempInc100000000);
        }
        else{
          answer.add(increment8);
        }
        tempY.add(tempInc10000000);
        if(tempY.isGreaterThan(this)){
          tempY.subtract(tempInc10000000);
        }
        else{
          answer.add(increment7);
        }
        tempY.add(tempInc1000000);
        if(tempY.isGreaterThan(this)){
          tempY.subtract(tempInc1000000);
        }
        else{
          answer.add(increment6);
        }
        tempY.add(tempInc100000);
        if(tempY.isGreaterThan(this)){
          tempY.subtract(tempInc100000);
        }
        else{
          answer.add(increment5);
        }
        tempY.add(tempInc10000);
        if(tempY.isGreaterThan(this)){
          tempY.subtract(tempInc10000);
        }
        else{
          answer.add(increment4);
        }
        tempY.add(tempInc1000);
        if(tempY.isGreaterThan(this)){
          tempY.subtract(tempInc1000);
        }
        else{
          answer.add(increment3);
        }
        tempY.add(tempInc100);
        if(tempY.isGreaterThan(this)){
          tempY.subtract(tempInc100);
        }
        else{
          answer.add(increment2);
        }
        tempY.add(tempInc10);
        if(tempY.isGreaterThan(this)){
          tempY.subtract(tempInc10);
        }
        else{
          answer.add(increment1);
        }
        tempY.add(tempInc);
        answer.add(increment);
      }
      tempY.subtract(tempInc);
      
      this.subtract(tempY);
      
      IntArrClass modAnswer = new IntArrClass(1);
      modAnswer.numArray = this.numArray;
      modAnswer.size = this.size;
      
      this.numArray = tempThis.numArray;
      this.size = tempThis.size;
      
      return modAnswer;
    }
    
}
