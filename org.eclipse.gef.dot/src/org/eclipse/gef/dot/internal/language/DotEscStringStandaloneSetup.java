/*
 * generated by Xtext
 */
package org.eclipse.gef.dot.internal.language;

/**
 * Initialization support for running Xtext languages 
 * without equinox extension registry
 */
public class DotEscStringStandaloneSetup extends DotEscStringStandaloneSetupGenerated{

	public static void doSetup() {
		new DotEscStringStandaloneSetup().createInjectorAndDoEMFRegistration();
	}
}

