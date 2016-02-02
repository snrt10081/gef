/*******************************************************************************
 * Copyright (c) 2014, 2015 itemis AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Matthias Wienand (itemis AG) - initial API and implementation
 *
 *******************************************************************************/
package org.eclipse.gef4.mvc.fx.parts;

import java.util.List;
import java.util.Set;

import org.eclipse.gef4.fx.nodes.GeometryNode;
import org.eclipse.gef4.fx.utils.NodeUtils;
import org.eclipse.gef4.geometry.planar.ICurve;
import org.eclipse.gef4.geometry.planar.IGeometry;
import org.eclipse.gef4.mvc.models.SelectionModel;
import org.eclipse.gef4.mvc.parts.IContentPart;
import org.eclipse.gef4.mvc.parts.IVisualPart;
import org.eclipse.gef4.mvc.viewer.IViewer;

import com.google.common.reflect.TypeToken;
import com.google.inject.Provider;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeType;

/**
 * The {@link FXSelectionFeedbackPart} is an {@link AbstractFXFeedbackPart} that
 * is parameterized by <code>GeometryNode&lt;IGeometry&gt;</code>.
 *
 * @author mwienand
 *
 */
public class FXSelectionFeedbackPart
		extends AbstractFXFeedbackPart<GeometryNode<IGeometry>> {

	/**
	 * The stroke width for selection feedback.
	 */
	public static final double STROKE_WIDTH = 1.5d;

	/**
	 * The primary selection {@link Color}.
	 */
	public static final Color PRIMARY_COLOR = Color.BLACK;

	/**
	 * The secondary selection {@link Color}.
	 */
	public static final Color SECONDARY_COLOR = Color.web("#666666");

	private Provider<? extends IGeometry> feedbackGeometryProvider;

	/**
	 * Default constructor.
	 */
	public FXSelectionFeedbackPart() {
	}

	@Override
	protected GeometryNode<IGeometry> createVisual() {
		GeometryNode<IGeometry> feedbackVisual = new GeometryNode<>();
		feedbackVisual.setFill(Color.TRANSPARENT);
		feedbackVisual.setMouseTransparent(true);
		feedbackVisual.setManaged(false);
		feedbackVisual.setStrokeWidth(STROKE_WIDTH);
		return feedbackVisual;
	}

	@SuppressWarnings("serial")
	@Override
	public void doRefreshVisual(GeometryNode<IGeometry> visual) {
		Set<IVisualPart<Node, ? extends Node>> anchorages = getAnchoragesUnmodifiable()
				.keySet();
		if (anchorages.isEmpty()) {
			return;
		}
		IGeometry feedbackGeometry = getFeedbackGeometry();
		if (feedbackGeometry == null) {
			return;
		}

		// update geometry
		visual.setGeometry(feedbackGeometry);

		// update stroke type
		if (feedbackGeometry instanceof ICurve) {
			// stroke centered
			visual.setStrokeType(StrokeType.CENTERED);
		} else {
			// stroke outside
			visual.setStrokeType(StrokeType.OUTSIDE);
			// TODO: adjust stroke width to get hair lines
		}

		// update color
		IVisualPart<Node, ? extends Node> anchorage = anchorages.iterator()
				.next();
		IViewer<Node> viewer = anchorage.getRoot().getViewer();
		List<IContentPart<Node, ? extends Node>> selected = viewer
				.getAdapter(new TypeToken<SelectionModel<Node>>() {
				}).getSelectionUnmodifiable();
		if (selected.get(0) == anchorage) {
			// primary selection
			visual.setStroke(PRIMARY_COLOR);
			// XXX: place before focus feedback
			visual.toFront();
		} else {
			// secondary selection
			visual.setStroke(SECONDARY_COLOR);
		}
	}

	/**
	 * Returns the {@link IGeometry} that is provided by this part's
	 * {@link #setGeometryProvider(Provider) feedback geometry provider}.
	 *
	 * @return The {@link IGeometry} that is provided by this part's
	 *         {@link #setGeometryProvider(Provider) feedback geometry provider}
	 *         .
	 */
	protected IGeometry getFeedbackGeometry() {
		return NodeUtils.sceneToLocal(getVisual(),
				feedbackGeometryProvider.get());
	}

	/**
	 * Sets the feedback geometry provider (
	 * <code>Provider&lt;IGeometry&gt;</code>) of this part to the given value.
	 *
	 * @param geometryProvider
	 *            The new feedback geometry provider for this part.
	 */
	public void setGeometryProvider(
			Provider<? extends IGeometry> geometryProvider) {
		feedbackGeometryProvider = geometryProvider;
	}

}
