# Project ChallengeClient Coding Standards

## General
This section lists the official conventions of the languages Kotlin and Java. This project tries to follow them as closely as possible and we expect outside developers to do the same when working on the client.

**Additional, non-standard conventions are listed below. These must also be followed.**

### Kotlin
* Follow Kotlin's official [code conventions](https://kotlinlang.org/docs/reference/coding-conventions.html#coding-conventions).
* Have a look at Kotlin's official [documentation](https://kotlinlang.org/docs/reference/).

### Java
* Have a look at Oracle's [Java Code PDF document](https://www.oracle.com/technetwork/java/codeconventions-150003.pdf).
* Read the Wikipedia article on [Java's Syntax](https://en.wikipedia.org/wiki/Java_syntax).
* Look at Oracle's [Java Tutorial](https://docs.oracle.com/javase/tutorial/java/).

# Files
### Generation

To document the ownership of a file, we include the following text in all code files *(.kt and .java)* at the beginning of the file:
```kotlin
/*
 * Challenge Client
 * https://github.com/MC-Challenge/ChallengeClient/
 
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.

 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.

 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
```

# Mixin
### How to Use

To Use Mixin, you have to follow, this step:

* Write over your class, the [annotation](https://de.wikipedia.org/wiki/Annotation_(Java)) ```@Mixin(YourClass.class)```, and replace 'YourClass', with the class you want to inject into.

### Inject into methods

To inject into methods, you have to create, a new method, in your mixin-class, and add the parameter, from the 'original' method. Also you have the parameter 'Callback'.
Then you write over this created method the following [annotation](https://de.wikipedia.org/wiki/Annotation_(Java))
 * ```@Inject(method = "yourMethodFromTheInjectClass", at = @At(value = "YOURFIELD", target = "YourTarget", shift = At.Shift.YOURSHIFT))```
