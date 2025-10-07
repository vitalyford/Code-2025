package Stacks;

public class BracesTest {
    
    // Test counter
    private static int testCount = 0;
    private static int passedTests = 0;
    
    // Helper method to run a test
    private static void runTest(String testName, String input, boolean expected) {
        testCount++;
        boolean result = Braces.isBalanced(input);
        if (result == expected) {
            passedTests++;
            System.out.println("✓ PASS: " + testName + " - Input: \"" + input + "\"");
        } else {
            System.out.println("✗ FAIL: " + testName + " - Input: \"" + input + "\"");
            System.out.println("   Expected: " + expected + ", Got: " + result);
        }
    }
    
    public static void main(String[] args) {
        System.out.println("Running Braces.isBalanced() Tests");
        System.out.println("=".repeat(50));
        
        // Test 1: Empty string
        runTest("Empty string", "", true);
        
        // Test 2: String with no braces
        runTest("No braces", "hello world", true);
        
        // Test 3: Simple balanced braces
        runTest("Simple balanced braces", "{}", true);
        runTest("Simple balanced brackets", "[]", true);
        
        // Test 4: Simple unbalanced braces
        runTest("Unmatched opening brace", "{", false);
        runTest("Unmatched closing brace", "}", false);
        runTest("Unmatched opening bracket", "[", false);
        runTest("Unmatched closing bracket", "]", false);
        
        // Test 5: Mismatched braces/brackets
        runTest("Mismatched brace-bracket", "{]", false);
        runTest("Mismatched bracket-brace", "[}", false);
        
        // Test 6: Nested balanced braces
        runTest("Nested braces", "{{}}}", false);
        runTest("Properly nested braces", "{{}}", true);
        runTest("Nested brackets", "[[]]", true);
        runTest("Mixed nested", "{[]}", true);
        runTest("Complex nested", "{[{}]}", true);
        
        // Test 7: Multiple pairs
        runTest("Multiple pairs", "{}[]", true);
        runTest("Multiple mixed pairs", "{}{[]}[]", true);
        
        // Test 8: With text content
        runTest("Text with braces", "abc{def}ghi", true);
        runTest("Text with brackets", "abc[def]ghi", true);
        runTest("Text with mixed", "abc{def[ghi]jkl}mno", true);
        
        // Test 9: Complex valid cases
        runTest("Complex valid 1", "abc{defg{[ijk]}{l{mn}}op}qr", true);
        runTest("Complex valid 2", "a{b[c{d}e]f}g", true);
        runTest("Complex valid 3", "{[{[{}]}]}", true);
        
        // Test 10: Complex invalid cases
        runTest("Complex invalid 1", "abc{defg{[ijk}{l{mn}}op}qr", false);
        runTest("Complex invalid 2", "a{b[c{d}e]f}g}", false);
        runTest("Complex invalid 3", "{[{[{}]}}", false);
        runTest("Complex invalid 4", "{{[}]}", false);
        
        // Test 11: Edge cases with wrong order
        runTest("Wrong closing order", "{[}]", false);
        runTest("Interleaved wrong order", "{[]}{[}]", false);
        
        // Test 12: Only text, no braces
        runTest("Only letters", "abcdefghijklmnop", true);
        runTest("Letters and numbers", "abc123def456", true);
        
        // Test 13: Special sequences
        runTest("Alternating", "{}{}{}[][]", true);
        runTest("All opening", "{{{[[[", false);
        runTest("All closing", "}}}]]]", false);
        
        // Test 14: Single character tests
        runTest("Single open brace", "{", false);
        runTest("Single close brace", "}", false);
        runTest("Single open bracket", "[", false);
        runTest("Single close bracket", "]", false);
        
        // Test 15: Real-world like examples
        runTest("Function-like", "function{if[condition]{body}}", true);
        runTest("Array-like", "array[index{key}]", true);
        runTest("JSON-like", "{key[value{nested}]}", true);
        
        // Test 16: Edge case - only closing braces
        runTest("Only closing mixed", "}]", false);
        runTest("Only closing same", "}}", false);
        
        // Test 17: Very deeply nested
        runTest("Deep nesting", "{{{{[[[[]]]]}}}}", true);
        runTest("Deep nesting invalid", "{{{{[[[[]]]}}}}", false);
        
        // Print summary
        System.out.println("=".repeat(50));
        System.out.println("Test Summary:");
        System.out.println("Total tests: " + testCount);
        System.out.println("Passed: " + passedTests);
        System.out.println("Failed: " + (testCount - passedTests));
        System.out.println("Success rate: " + String.format("%.1f", (passedTests * 100.0 / testCount)) + "%");
        
        if (passedTests == testCount) {
            System.out.println("🎉 All tests passed!");
        } else {
            System.out.println("⚠️  Some tests failed. Please review the implementation.");
        }
    }
}