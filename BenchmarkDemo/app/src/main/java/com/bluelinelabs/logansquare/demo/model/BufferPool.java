package com.bluelinelabs.logansquare.demo.model;
/*
 * Copyright (C) 2017 Google Inc.
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



/**
 * A pool of byte buffer. This class will be held by the Gson instance and is intended
 * to recycle the char buffer being used by the {@code JsonReader}.
 *
 * @author Mohammad Yasir
 * @since 2.8.1
 */
public class BufferPool {
    private final char[][] charBuffer;
    private int currentIndex;

    public BufferPool(int capacity) {
        charBuffer = new char[capacity][];
        currentIndex = -1;
    }

    synchronized char[] acquire() {
        return (currentIndex > -1) ? charBuffer[currentIndex--] : new char[1024];
    }

    synchronized void release(char[] buffer) {
        if(currentIndex + 1 < charBuffer.length) {
            charBuffer[++currentIndex] = buffer;
        }
    }
}
