//$HeadURL: svn+ssh://aschmitz@deegree.wald.intevation.de/deegree/deegree3/trunk/deegree-core/deegree-core-rendering-2d/src/main/java/org/deegree/rendering/r2d/styling/components/Fill.java $
/*----------------------------------------------------------------------------
 This file is part of deegree, http://deegree.org/
 Copyright (C) 2001-2009 by:
   Department of Geography, University of Bonn
 and
   lat/lon GmbH

 This library is free software; you can redistribute it and/or modify it under
 the terms of the GNU Lesser General Public License as published by the Free
 Software Foundation; either version 2.1 of the License, or (at your option)
 any later version.
 This library is distributed in the hope that it will be useful, but WITHOUT
 ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 details.
 You should have received a copy of the GNU Lesser General Public License
 along with this library; if not, write to the Free Software Foundation, Inc.,
 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA

 Contact information:

 lat/lon GmbH
 Aennchenstr. 19, 53177 Bonn
 Germany
 http://lat-lon.de/

 Department of Geography, University of Bonn
 Prof. Dr. Klaus Greve
 Postfach 1147, 53001 Bonn
 Germany
 http://www.geographie.uni-bonn.de/deegree/

 e-mail: info@deegree.org
----------------------------------------------------------------------------*/

package org.deegree.style.styling.components;

import java.awt.Color;

import org.deegree.style.styling.Copyable;

/**
 * <code>Fill</code>
 *
 * @author <a href="mailto:schmitz@lat-lon.de">Andreas Schmitz</a>
 * @author last edited by: $Author: mschneider $
 *
 * @version $Revision: 18171 $, $Date: 2009-06-17 16:00:07 +0200 (Wed, 17 Jun 2009) $
 */
public class Fill implements Copyable<Fill> {

    /**
     * Default is gray (#808080).
     */
    public Color color = new Color( 128, 128, 128, 255 );

    /**
     * Default is no graphic fill.
     */
    public Graphic graphic;

    public Fill copy() {
        Fill copy = new Fill();
        copy.color = color;
        copy.graphic = graphic == null ? null : graphic.copy();
        return copy;
    }

}