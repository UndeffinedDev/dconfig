> [!WARNING]
> This project is in alpha phase and is a school prototype, so you may NOT find it as comfortable as other configuration files (yaml, toml, etc...) but I will try to improve it.

> [!CAUTION]
> Don't use the convertValue() method at all, as it will be removed in the next update.

#### Author:
Sergio Caguana (UndeffinedDev)

## How To Use - DConfParser (DConfig Interpreter)

### Overview

`DConfParser` is a Java class designed to parse, manipulate, and save configuration files with a `.dconf` extension. The `.dconf` format is similar to INI files, supporting sections, keys, and lists of values. This guide will walk you through how to use the `DConfParser` class.

### 1. Adding the Dependency

Before using `DConfParser`, ensure it's part of your project. You can either:

- Include the `.jar` file in your classpath.
- Add the package to your project.

#### 2. **Creating a DConfParser Instance**

To initialize the parser, provide the path to a `.dconf` file and the character encoding you wish to use (e.g., `UTF-8`):

```java 
import me.undeffineddev.libdataconfig.DConfParser;
import me.undeffineddev.libdataconfig.exceptions.InvalidFileException;

import java.nio.charset.StandardCharsets;

public class Main {
    public static void main(String[] args) {
        try {
            DConfParser parser = new DConfParser("config.dconf", StandardCharsets.UTF_8);
        } catch (InvalidFileException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
```

This will automatically attempt to load the configuration file if it exists and if it has the `.dconf` extension.

#### 3. **Reading Data from the Configuration**

You can read specific keys from sections using `getList()` or `get()`.

- **Get a List of Values**: This method retrieves values for a key in a specific section. If the values are quoted (either single or double quotes), it will remove the quotes and return the cleaned values.

```java
List<String> values = parser.getList("database", "hosts");
System.out.println(values);  // Output: [localhost, 192.168.1.1]
```

- **Get a Single Value**: You can also retrieve a single value and convert it to the desired type, using `get()`:

```java
String host = parser.get("database", "host", "localhost"); 
int port = parser.get("database", "port", 5432); 
boolean useSSL = parser.get("database", "useSSL", false);
```

#### 4. **Checking for Sections or Keys**

Before retrieving data, you may want to check if a section or key exists:

- **Check for a Section**: Use `containsSection()` to verify if a section is present:

```java
if (parser.containsSection("database")) {     
	// Proceed with further operations 
}
```

- **Check for a Key**: Use `containsKey()` to check if a key exists in a section:

```java
if (parser.containsKey("database", "host")) {     
    String host = parser.get("database", "host", "localhost");
}
```

#### 5. **Modifying Data**

To modify or add new configuration entries, use the `set()` method. This method expects values in a list format, and it automatically wraps the values in quotes when saving.


```java 
List<String> newHosts = Arrays.asList("localhost", "192.168.1.2");
parser.set("database", "hosts", newHosts);
```

#### 6. **Saving Changes to the File**

After modifying the configuration, you must save the changes to persist them in the `.dconf` file:


```java
try {
    parser.save();
} catch (IOException e) {
    System.err.println("Error saving the configuration: " + e.getMessage());
}
```

#### 7. **Creating a New Section**

If you need to add a completely new section to the configuration, use the `createSection()` method:

```java 
parser.createSection("server"); 
parser.set("server", "ip", Arrays.asList("127.0.0.1"));
parser.set("server", "port", Arrays.asList("8080"));
```

#### 9. **Handling Errors**

Make sure to handle potential exceptions when loading, saving, or manipulating the configuration. `InvalidFileException` may be thrown when loading files with incorrect extensions, and `IOException` may be encountered when working with file I/O operations.

#### Example Usage:

Here’s a full example:

```java
import me.undeffineddev.libdataconfig.DConfParser;
import me.undeffineddev.libdataconfig.exceptions.InvalidFileException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class Example {
    public static void main(String[] args) {
        try {
            // Load the config file
            DConfParser parser = new DConfParser("config.dconf", StandardCharsets.UTF_8);
            
            // Read values
            String host = parser.get("database", "host", "localhost");
            System.out.println("Database Host: " + host);
            
            // Modify the configuration
            parser.createSection("server");
            parser.set("server", "port", Arrays.asList("8080"));
            
            // Save the changes
            parser.save();
        } catch (InvalidFileException | IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
```

With this guide, you should be able to load, modify, and save `.dconf` files using the `DConfParser` class.
