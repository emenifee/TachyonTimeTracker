/**
 * The MIT License (MIT)
 * 
 * Copyright (c) 2014 Ton Broekhuizen
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package org.tonoplace.mcmods.timetracker.client;

/**
 * @author BlackTachyon
 * 
 * Code pulled from Tonius Tweaks by IDEA:
 * Master branch of Tonis/SimplyJetpacks 
 * SimplyJetpacks/src/main/java/tonius/simplyjetpacks/config/Section.java on 20151101_1056 CST
 * https://github.com/Tonius/SimplyJetpacks/blob/master/src/main/java/tonius/simplyjetpacks/config/Section.java
 * 
 * Modified package to work in this mod.
 */
public class Section {
   public final boolean client;
   public final String name;
   public final String id;
   
   public Section(boolean client, String name, String id) {
       this.client = client;
       this.name = name;
       this.id = id;
       Config.configSections.add(this);
   }
   
   public String toLowerCase() {
       return this.name.toLowerCase();
   }
}
