package org.example.lab6;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.control.Alert;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DrawingPanel
{
	private static final double RADIUS = 17;
	private static final double THICKNESS_LARGE = 8;
	private static final double THICKNESS_SMALL = 0.5;
	private static final List<Line> lines = new ArrayList<>();
	private static final List<Circle> circles = new ArrayList<>();
	private static int rows;
	private static int columns;

	public static void createMatrix(AnchorPane gridPane, int rows, int columns)
	{
		gridPane.getChildren().clear();
		lines.clear();
		circles.clear();
		DrawingPanel.rows = rows;
		DrawingPanel.columns = columns;
		double width = gridPane.getWidth();
		double height = gridPane.getHeight();
		double cellWidth = width / columns;
		double cellHeight = height / rows;

		double circleRadius = Math.min(RADIUS, Math.min(cellWidth, cellHeight) / 4);
		double startX = (width - columns * cellWidth) / 2;
		double startY = (height - rows * cellHeight) / 2;

		addLinesToGrid(gridPane, cellWidth, cellHeight, startX, startY, true);
		addLinesToGrid(gridPane, cellWidth, cellHeight, startX, startY, false);
		addCirclesToGrid(gridPane, cellWidth, cellHeight, startX, startY, circleRadius);
		disableCirclesWithoutThickLineAdjacent();
	}

	private static void addCirclesToGrid(AnchorPane gridPane, double cellWidth, double cellHeight, double startX, double startY, double circleRadius)
	{
		for (int i = 0; i <= rows; i++)
		{
			for (int j = 0; j <= columns; j++)
			{
				double circleX = startX + (j * cellWidth);
				double circleY = startY + (i * cellHeight);
				Circle circle = createCircle(circleX, circleY, circleRadius);
				circles.add(circle);
				gridPane.getChildren().add(circle);
			}
		}
	}

	private static Circle createCircle(double x, double y, double radius)
	{
		Circle circle = new Circle(x, y, radius);
		circle.setFill(Color.GRAY);
		circle.setOnMouseClicked(event -> modifyCircleColor(circle));
		return circle;
	}

	private static void addLinesToGrid(AnchorPane gridPane, double cellWidth, double cellHeight, double startX, double startY, boolean isHorizontal)
	{
		Random random = new Random();

		int rowCount = rows;
		int colCount = columns;
		if (isHorizontal)
		{
			rowCount = rows + 1;
		}
		else
		{
			colCount = columns + 1;
		}

		for (int i = 0; i < rowCount; i++)
		{
			for (int j = 0; j < colCount; j++)
			{
				double x1, y1, x2, y2;
				if (isHorizontal)
				{
					y1 = y2 = startY + (i * cellHeight);
					x1 = startX + (j * cellWidth);
					x2 = startX + ((j + 1) * cellWidth);
				}
				else
				{
					x1 = x2 = startX + (j * cellWidth);
					y1 = startY + (i * cellHeight);
					y2 = startY + ((i + 1) * cellHeight);
				}
				addLine(gridPane, x1, y1, x2, y2, random.nextBoolean() ? THICKNESS_LARGE : THICKNESS_SMALL);
			}
		}
	}

	private static void addLine(AnchorPane gridPane, double startX, double startY, double endX, double endY, double strokeWidth)
	{
		Line line = createLine(startX, startY, endX, endY, strokeWidth);
		lines.add(line);
		gridPane.getChildren().add(line);
	}

	private static Line createLine(double startX, double startY, double endX, double endY, double strokeWidth)
	{
		Line line = new Line(startX, startY, endX, endY);
		line.setStrokeWidth(strokeWidth);
		line.setStroke(Color.BLACK);
		return line;
	}

	private static void modifyCircleColor(Circle circle)
	{
		if (circle.getFill().equals(Color.GRAY))
		{
			circle.setFill((Game.turn % 2 == 0) ? Game.PLAYER_2_COLOR : Game.PLAYER_1_COLOR);
			Game.turn++;
		}
	}

	private static void disableCirclesWithoutThickLineAdjacent()
	{
		for (Circle circle : circles)
		{
			if (!hasThickLineAdjacent(circle))
			{
				circle.setDisable(true);
				circle.setFill(Color.TRANSPARENT);
				circle.setStroke(Color.GRAY);
			}
		}
	}

	private static boolean hasThickLineAdjacent(Circle circle)
	{
		double circleX = circle.getCenterX();
		double circleY = circle.getCenterY();
		for (Line line : lines)
		{
			double lineStartX = line.getStartX();
			double lineStartY = line.getStartY();
			double lineEndX = line.getEndX();
			double lineEndY = line.getEndY();
			if ((lineStartX == circleX && lineStartY == circleY) || (lineEndX == circleX && lineEndY == circleY))
			{
				if (line.getStrokeWidth() == THICKNESS_LARGE)
				{
					return true;
				}
			}
		}
		return false;
	}

	public static void exportImage(AnchorPane gridPane, String filePath)
	{
		WritableImage image = gridPane.snapshot(null, null);

		File file = new File(filePath);
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		try
		{
			ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
			alert.setTitle("Success");
			alert.setHeaderText("Image exported successfully");
			alert.setContentText("Image saved as " + filePath + " in the project directory.");

			alert.showAndWait();
		}
		catch (IOException e)
		{
			alert.setTitle("Error");
			alert.setHeaderText("Error exporting image");
			alert.setContentText("An error occurred while exporting the image.");

			alert.showAndWait();
		}
	}
}
