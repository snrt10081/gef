/*******************************************************************************
 * Copyright (c) 2014, 2015 itemis AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Alexander Nyßen (itemis AG) - initial API and implementation
 *     Matthias Wienand (itemis AG) - contribution for bug #483710
 *
 *******************************************************************************/
package org.eclipse.gef.mvc.fx.providers;

import org.eclipse.gef.geometry.planar.IGeometry;

import com.google.inject.Provider;

import javafx.scene.shape.Rectangle;

/**
 * The {@link GeometricBoundsProvider} is a {@link Provider Provider
 * &lt;IGeometry&gt;} that returns a {@link Rectangle} that corresponds to the
 * geometric bounds of its host visual, i.e. it does not include the stroke of
 * the visual or other visual properties (e.g. clip or effect). The
 * {@link Rectangle} is specified within the local coordinate system of the host
 * visual.
 *
 * @author anyssen
 * @author mwienand
 *
 */
public class GeometricBoundsProvider extends GeometricOutlineProvider {

	@Override
	public IGeometry get() {
		return super.get().getBounds();
	}

}
