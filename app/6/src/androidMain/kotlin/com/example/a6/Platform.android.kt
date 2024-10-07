package com.example.a6

/**
 * Returns a platform-specific string.
 *
 * This function acts as a facade, providing a consistent interface for retrieving a string
 * whose value may vary depending on the target platform (JVM, JS, Native).
 *
 * Platform-specific implementations should override this function to provide
 * tailored logic or data relevant to their respective environments.
 * This allows for platform-specific behavior while maintaining a common interface
 * across all platforms.
 *
 * **Examples of platform-specific implementations:**
 *  - **JVM:** Return system information like the OS version or Java runtime details.
 *  - **JS:** Return browser details, interact with the DOM, or access web APIs.
 *  - **Native:** Access specific hardware features or interact with the native OS.
 *
 * **Default Implementation (Common):**
 * If no platform-specific implementation is provided for the current platform,
 * this function returns a generic New Year's message, displaying the remaining
 * days until New Year's Eve.
 *
 * @return A platform-dependent string or a default New Year's message if no
 * platform-specific implementation is available.
 * @see daysUntilNewYear Calculates the remaining days until New Year's Eve.
 */
actual fun platform() = "There are only ${daysUntilNewYear()} days left until New Year! 🎆"