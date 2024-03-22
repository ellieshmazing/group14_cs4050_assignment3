package assignment.dictionary;

/*

 */

//
import java.util.*;
import java.io.*;
import java.util.Dictionary;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.BiFunction;

/**
 A Hashtable implementation for a simple spell checker. Without any requirement of information for each entry beyond
 its spelling, the keys and values are identical. The key is hashed to an integer that represents the value's index in
 the table, but external users only have to know the word they're searching for.
 */
public class MyHashTable<K,V>
//        
//        
{
    private V[] table;
    private int currentSize;
    private int capacity = 1500;

    //Default constructor function takes no parameters. Initializes empty array of V type with 0 entries.
    //Array represents hashtable.
    public MyHashTable(){
        table = (V[]) new Object[capacity];
        currentSize = 0;
    }

    //Function to get hash location from @value.
    //Uses Object's hashCode() method to handle type issues. Modulo limits range to the capacity of the hashtable.
    private int hash(Object key){
        int hc = key.hashCode();
        return (hc & 0x7fffffff) % capacity;
//       return key.hashCode() % capacity;
    }

    //Function to add new value to hashtable.
    //Generates hash location than linearly probes for available slot.
    public V put(K key, V value) {
        //Use hash function on key
        int index = hash(key);

        //Probe until existing entry of value or empty slot found
        while (table[index] != null){
            if (table[index] == value){
                break;
            }

            index++;
        }

        //Replaces value in hashtable
        V temp = table[index];
        table[index] = value;

        //Increments size
        currentSize++;

        //Returns replaced value
        return temp;
    }

    //Removes element associated with key from table
    public V remove(Object key){
        //Generate default hash value
        int index = hash(key);

        //Search until desired key is found or lack of inclusion is certain
        while(table[index] != null){
            if (table[index] == key)
            {
                break;
            }

            index++;
        }

        //Remove entry value
        V temp = table[index];
        table[index] = null;

        //Decrements size
        currentSize--;

        //Return removed value
        return temp;
    }

    //Finds value associated with key, or null if not found
    public V get(Object key){
        //Generate default hash value
        int index = hash(key);

        //Search until desired key is found or lack of inclusion is certain
        while(table[index] != null){
            if (table[index] == key)
            {
                break;
            }

            index++;
        }

        //Return found value, or null otherwise
        return table[index];
    }

    //Finds whether key is in dictionary
    public boolean containsKey(Object key){
        //Generate default hash value
        int index = hash(key);

        //Search until desired key is found or lack of inclusion is certain
        while(table[index] != null){
            //Return if found
            if (table[index].equals(key))
            {
                return true;
            }

            index++;
        }

        //Return false if not found
        return false;
    }

    //Returns AList of keys in hashtable
    public AList<K> keySet(){
        //Declare list of K-type elements
        AList<K> keys = new AList<K>(currentSize);

        //Iterate through table, adding filled values
        for (int i = 0; i < capacity; i++)
        {
            if (table[i] != null)
            {
                keys.add((K) table[i]);
            }
        }

        return keys;
    }

    //Returns AList of values in hashtable
    //Identical to keySet() as values and keys are interchangeable
    public AList<V> values(){
        AList<V> valueList = new AList<V>(currentSize);

        for (int i = 0; i < capacity; i++)
        {
            if (table[i] != null)
            {
                valueList.add(table[i]);
            }
        }

        return valueList;
    }

    //Checks whether MyHashTable contains any elements.
    public boolean isEmpty(){
        if (currentSize == 0)
        {
            return true;
        }

        return false;
    }

    //Returns size of table
    public int size(){
        return currentSize;
    }

    //Empties hashtable
    public void clear(){
        table = (V[]) new Object[capacity];
        currentSize = 0;
    }

    //Checks if two hashtables are equivalent
    public boolean equals(Object other){
        return (this == other);
    }

    //Returns string version of hashtable
    public String toString(){
        return Arrays.toString(this.values().toArray());
    }
}


