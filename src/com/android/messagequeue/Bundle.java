/*
 * Copyright (C) 2007 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.messagequeue;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

/**
 * A mapping from String values to various Parcelable types.
 */
public final class Bundle implements Serializable, Cloneable {
    private static final String TAG = "Bundle";
    static final boolean DEBUG = false;

    static final int BUNDLE_MAGIC = 0x4C444E42; // 'B' 'N' 'D' 'L'
    // Invariant - exactly one of mMap / mParcelledData will be null
    // (except inside a call to unparcel)

    /* package */LinkedHashMap<String, Object> mMap = null;

    /**
     * The ClassLoader used when unparcelling data from mParcelledData.
     */
    private ClassLoader mClassLoader;

    /**
     * Constructs a new, empty Bundle.
     */
    public Bundle() {
        mMap = new LinkedHashMap<String, Object>();
        mClassLoader = getClass().getClassLoader();
    }

    /**
     * Constructs a new, empty Bundle that uses a specific ClassLoader for
     * instantiating Parcelable and Serializable objects.
     * 
     * @param loader An explicit ClassLoader to use when instantiating objects
     *            inside of the Bundle.
     */
    public Bundle(ClassLoader loader) {
        mMap = new LinkedHashMap<String, Object>();
        mClassLoader = loader;
    }

    /**
     * Constructs a new, empty Bundle sized to hold the given number of
     * elements. The Bundle will grow as needed.
     * 
     * @param capacity the initial capacity of the Bundle
     */
    public Bundle(int capacity) {
        mMap = new LinkedHashMap<String, Object>(capacity);
        mClassLoader = getClass().getClassLoader();
    }

    /**
     * Constructs a Bundle containing a copy of the mappings from the given
     * Bundle.
     * 
     * @param b a Bundle to be copied.
     */
    public Bundle(Bundle b) {
        if (b.mMap != null) {
            mMap = new LinkedHashMap<String, Object>(b.mMap);
        } else {
            mMap = null;
        }
        mClassLoader = b.mClassLoader;
    }


    /**
     * Changes the ClassLoader this Bundle uses when instantiating objects.
     * 
     * @param loader An explicit ClassLoader to use when instantiating objects
     *            inside of the Bundle.
     */
    public void setClassLoader(ClassLoader loader) {
        mClassLoader = loader;
    }

    /**
     * Return the ClassLoader currently associated with this Bundle.
     */
    public ClassLoader getClassLoader() {
        return mClassLoader;
    }

    /**
     * Clones the current Bundle. The internal map is cloned, but the keys and
     * values to which it refers are copied by reference.
     */
    @Override
    public Object clone() {
        return new Bundle(this);
    }


    /**
     * Returns the number of mappings contained in this Bundle.
     * 
     * @return the number of mappings as an int.
     */
    public int size() {
        return mMap.size();
    }

    /**
     * Returns true if the mapping of this Bundle is empty, false otherwise.
     */
    public boolean isEmpty() {
        return mMap.isEmpty();
    }

    /**
     * Removes all elements from the mapping of this Bundle.
     */
    public void clear() {
        mMap.clear();
    }

    /**
     * Returns true if the given key is contained in the mapping of this Bundle.
     * 
     * @param key a String key
     * @return true if the key is part of the mapping, false otherwise
     */
    public boolean containsKey(String key) {
        return mMap.containsKey(key);
    }

    /**
     * Returns the entry with the given key as an object.
     * 
     * @param key a String key
     * @return an Object, or null
     */
    public Object get(String key) {
        return mMap.get(key);
    }

    /**
     * Removes any entry with the given key from the mapping of this Bundle.
     * 
     * @param key a String key
     */
    public void remove(String key) {
        mMap.remove(key);
    }

    /**
     * Inserts all mappings from the given Bundle into this Bundle.
     * 
     * @param map a Bundle
     */
    public void putAll(Bundle map) {
        mMap.putAll(map.mMap);
    }

    /**
     * Returns a Set containing the Strings used as keys in this Bundle.
     * 
     * @return a Set of String keys
     */
    public Set<String> keySet() {
        return mMap.keySet();
    }

    @Override
    public synchronized String toString() {
        return "Bundle[" + mMap.toString() + "]";
    }
}
