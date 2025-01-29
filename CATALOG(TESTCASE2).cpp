#include <iostream>
#include <vector>
#include <string>
#include <cmath>
#include <sstream>
#include <map>

// Function to decode a number from a given base to decimal
long long decodeBase(const std::string& value, int base) {
    long long result = 0;
    for (char digit : value) {
        result *= base;
        if (digit >= '0' && digit <= '9')
            result += (digit - '0');
        else if (digit >= 'a' && digit <= 'f')
            result += (digit - 'a' + 10);
    }
    return result;
}

// Lagrange Interpolation to compute constant term (evaluating at x=0)
long long lagrangeInterpolation(const std::vector<std::pair<int, long long>>& points, int k) {
    long long c = 0;
    for (int i = 0; i < k; ++i) {
        long long term = points[i].second;
        for (int j = 0; j < k; ++j) {
            if (i != j) {
                term *= (0 - points[j].first);
                term /= (points[i].first - points[j].first);
            }
        }
        c += term;
    }
    return c;
}

int main() {
    // Test case 1 (you can change the input here)
    std::map<int, std::pair<int, std::string>> roots = {
        {1, {10, "4"}},
        {2, {2, "111"}},
        {3, {10, "12"}},
        {6, {4, "213"}}
    };

    int k = 3;  // We are given 3 roots, so polynomial degree is k - 1
    std::vector<std::pair<int, long long>> points;
    
    // Decode all the roots
    for (auto& root : roots) {
        int x = root.first;
        int base = root.second.first;
        std::string value = root.second.second;
        long long y = decodeBase(value, base);
        points.push_back({x, y});
    }

    // Compute the constant term c using Lagrange interpolation
    long long constantTerm = lagrangeInterpolation(points, k);
    
    // Output the constant term
    std::cout << "Constant term (c): " << constantTerm << std::endl;

    return 0;
}