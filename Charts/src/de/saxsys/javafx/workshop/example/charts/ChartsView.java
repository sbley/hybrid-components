package de.saxsys.javafx.workshop.example.charts;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.ListBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TextField;

import com.google.common.base.Predicate;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.Iterables;
import com.google.common.collect.Multiset;

/**
 * 
 * @author michael.thiele@saxsys.de
 *
 */

public class ChartsView {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private BarChart<String, Integer> barChart;

	@FXML
	private PieChart pieChart;

	@FXML
	private TextField textField;

	@FXML
	void initialize() {
		assert barChart != null : "fx:id=\"barChart\" was not injected: check your FXML file 'charts.fxml'.";
		assert pieChart != null : "fx:id=\"pieChart\" was not injected: check your FXML file 'charts.fxml'.";
		assert textField != null : "fx:id=\"textField\" was not injected: check your FXML file 'charts.fxml'.";

		initPieChart();
		initBarChart();
	}

	private void initPieChart() {
		// this list is set as input data for the chart (Data = tuple(String,
		// Integer))
		final ObservableList<Data> charList = FXCollections
				.observableArrayList();

		// the binding that ensures updates are propagated to the pie chart
		final ListBinding<Data> charBinding = new ListBinding<Data>() {
			{
				// whenever the text changes
				super.bind(textField.textProperty());
			}

			@Override
			protected ObservableList<Data> computeValue() {
				final ObservableList<Data> result = charList;

				final String string = textField.getText();
				final Multiset<Character> chars = HashMultiset.create();
				for (int i = 0; i < string.length(); i++) {
					chars.add(string.charAt(i));
				}

				removeUnusedChars(result, chars);

				for (final char c : chars.elementSet()) {
					final Data find = Iterables.find(result,
							new Predicate<Data>() {
								@Override
								public boolean apply(final Data input) {
									return input.getName().equals(
											String.valueOf(c));
								}
							}, null);
					if (find == null) {
						// a new char has been added, so create a new Data set
						result.add(new Data(String.valueOf(c), chars.count(c)));
					} else {
						// the char count is set to the new value
						find.setPieValue(chars.count(c));
					}
				}
				return result;
			}

			private void removeUnusedChars(final ObservableList<Data> result,
					final Multiset<Character> chars) {
				Iterables.removeIf(result, new Predicate<Data>() {
					@Override
					public boolean apply(final Data input) {
						return !chars.contains(input.getName().charAt(0));
					}
				});
			}
		};

		pieChart.setData(charList);
		Bindings.bindContent(charList, charBinding);
	}

	private void initBarChart() {
		// series is set as the input data for the bar chart
		final XYChart.Series<String, Integer> series = new XYChart.Series<>();
		// a data set for one series
		final ObservableList<XYChart.Data<String, Integer>> charList = FXCollections
				.observableArrayList();

		final ListBinding<XYChart.Data<String, Integer>> listBinding = new ListBinding<XYChart.Data<String, Integer>>() {
			{
				super.bind(textField.textProperty());
			}

			@SuppressWarnings("unchecked")
			@Override
			protected ObservableList<XYChart.Data<String, Integer>> computeValue() {
				final ObservableList<XYChart.Data<String, Integer>> result = charList;
				final String string = textField.getText();
				final Multiset<Character> chars = HashMultiset.create();
				for (int i = 0; i < string.length(); i++) {
					chars.add(string.charAt(i));
				}

				removeUnusedChars(result, chars);

				for (final char c : chars.elementSet()) {
					final XYChart.Data<String, Integer> find = Iterables.find(
							result,
							new Predicate<XYChart.Data<String, Integer>>() {
								@Override
								public boolean apply(
										final XYChart.Data<String, Integer> input) {
									return input.getXValue().equals(
											String.valueOf(c));
								}
							}, null);
					if (find == null) {
						// a new char has been added, so create a new Data set
						result.add(new XYChart.Data<String, Integer>(String
								.valueOf(c), chars.count(c)));
					} else {
						// the char count is set to the new value
						find.setYValue(chars.count(c));
					}
				}
				return result;
			}

			private void removeUnusedChars(
					final ObservableList<XYChart.Data<String, Integer>> result,
					final Multiset<Character> chars) {
				Iterables.removeIf(result,
						new Predicate<XYChart.Data<String, Integer>>() {
							@Override
							public boolean apply(
									final XYChart.Data<String, Integer> input) {
								return !chars.contains(input.getXValue()
										.charAt(0));
							}
						});
			}
		};

		barChart.getData().add(series);
		series.setData(charList);
		Bindings.bindContent(charList, listBinding);
	}

}
