/*******************************************************************************
 * Copyright (c) 2015, 2016 itemis AG and others.
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
package org.eclipse.gef.layout;

import org.eclipse.gef.graph.Edge;
import org.eclipse.gef.graph.Node;

/**
 * An {@link ILayoutFilter} can be used to filter layout objects, so that they
 * are not reported to any {@link ILayoutAlgorithm}.
 */
public interface ILayoutFilter {

	/**
	 * Returns <code>true</code> to indicate that the given {@link Edge} is
	 * irrelevant for layout. Otherwise returns <code>false</code>.
	 * 
	 * @param edge
	 *            The {@link Edge} which may be irrelevant for layout.
	 * @return <code>true</code> to indicate that the given {@link Edge} is
	 *         irrelevant for layout, otherwise <code>false</code>.
	 */
	public boolean isLayoutIrrelevant(Edge edge);

	/**
	 * Returns <code>true</code> to indicate that the given {@link Node} is
	 * irrelevant for layout. Otherwise returns <code>false</code>.
	 * 
	 * @param node
	 *            The {@link Node} which may be irrelevant for layout.
	 * @return <code>true</code> to indicate that the given {@link Node} is
	 *         irrelevant for layout, otherwise <code>false</code>.
	 */
	public boolean isLayoutIrrelevant(Node node);

}
