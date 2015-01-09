/*******************************************************************************
 * Copyright (c) 2014 itemis AG and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Matthias Wienand (itemis AG) - initial API & implementation
 *
 *******************************************************************************/
package org.eclipse.gef4.zest.fx.policies;

import java.util.Set;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import org.eclipse.gef4.graph.Node;
import org.eclipse.gef4.mvc.fx.policies.AbstractFXTypePolicy;
import org.eclipse.gef4.mvc.viewer.IViewer;
import org.eclipse.gef4.zest.fx.models.HidingModel;
import org.eclipse.gef4.zest.fx.parts.NodeContentPart;

public class HideOnTypePolicy extends AbstractFXTypePolicy {

	@Override
	public NodeContentPart getHost() {
		return (NodeContentPart) super.getHost();
	}

	@Override
	public void pressed(KeyEvent event) {
		KeyCode keyCode = event.getCode();
		if (KeyCode.P.equals(keyCode)) {
			prune();
		} else if (KeyCode.E.equals(keyCode)) {
			unprune();
		}
	}

	protected void prune() {
		getHost().<HideNodePolicy> getAdapter(HideNodePolicy.class).prune();
	}

	@Override
	public void released(KeyEvent event) {
	}

	protected void unprune() {
		IViewer<javafx.scene.Node> viewer = getHost().getRoot().getViewer();
		HidingModel pruningModel = viewer.getDomain().getAdapter(
				HidingModel.class);
		Set<Node> prunedNeighbors = pruningModel.getHiddenNeighbors(getHost()
				.getContent());
		if (prunedNeighbors != null && !prunedNeighbors.isEmpty()) {
			for (Node node : prunedNeighbors) {
				viewer.getContentPartMap().get(node)
						.<HideNodePolicy> getAdapter(HideNodePolicy.class)
						.unprune();
			}
		}
	}

}