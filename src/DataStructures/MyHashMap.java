package DataStructures;

import java.util.LinkedList;

public class MyHashMap<K, V> {
    static class Entry<K, V> {
        K key;
        V val;
        Entry(K k, V val) {
            this.key = k;
            this.val = val;
        }
    }
    private final int initial = 16;
    private LinkedList<Entry<K, V>>[] buckets = new LinkedList[initial];

    MyHashMap() {
        for (int i = 0; i < initial; i++) {
            buckets[i] = new LinkedList<>();
        }
    }

    public void put(K key, V val) {
        LinkedList<Entry<K, V>> bucket = buckets[getBucketIndex(key)];
        for (MyHashMap.Entry<K, V> map : bucket) {
            if (map.key == key) {
                map.val = val;
                return;
            }
        }
        bucket.add(new Entry<>(key, val));
    }
    public V get(K key){
        LinkedList<Entry<K, V>> bucket = buckets[getBucketIndex(key)];
        for (MyHashMap.Entry<K, V> map : bucket) {
            if (map.key == key) {
                return map.val;
            }
        }
        return null;
    }
    public void remove(K key){
        LinkedList<Entry<K, V>> bucket = buckets[getBucketIndex(key)];
        bucket.removeIf(entry->entry.key.equals(key));
    }
    private int getBucketIndex(K key) {
        return (key == null ? 0 : Math.abs(key.hashCode())) % initial;
    }

    public static void main(String[] args) {
        MyHashMap<String,String> map = new MyHashMap<>();
        map.put("a","agsgas");
        map.put("b",".jwhdhghs");
        System.out.println(map.get("a"));
        map.remove("a");
        System.out.println(map.get("b"));
        System.out.println(map.get("a"));

    }
}
