package helpers

// IsInArea provides methods to check if a point is within a specified area.
func IsInArea(x, y float64) bool {
	return (x >= -4 && x <= 4) && (y >= -3 && y <= 3) &&
		(inRect(x, y) || inTriangle(x, y) || inCircle(x, y))
}

// inRect checks if the point (x, y) is within the rectangle.
func inRect(x, y float64) bool {
	return x >= 0 && x <= 1 && y <= 0 && y >= -1
}

// inTriangle checks if the point (x, y) is within the triangle.
func inTriangle(x, y float64) bool {
	return (y >= 0) && (x <= 0) && (y <= x+1)
}

// inCircle checks if the point (x, y) is within the circle.
func inCircle(x, y float64) bool {
	return (x >= 0) && (y >= 0) && (x*x+y*y <= 0.25)
}
