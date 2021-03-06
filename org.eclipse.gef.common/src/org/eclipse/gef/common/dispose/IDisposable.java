/******************************************************************************
 * Copyright (c) 2015 itemis AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Alexander Nyßen (itemis AG) - initial API and implementation
 *     
 *******************************************************************************/
package org.eclipse.gef.common.dispose;

/**
 * An {@link IDisposable} needs to be disposed after it is no longer needed.
 * 
 * @author anyssen
 *
 */
public interface IDisposable {

	/**
	 * Called to dispose the {@link IDisposable}.
	 */
	public void dispose();
}
