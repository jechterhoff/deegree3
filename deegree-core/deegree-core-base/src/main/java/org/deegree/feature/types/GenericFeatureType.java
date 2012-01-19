//$HeadURL$
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
package org.deegree.feature.types;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.xml.namespace.QName;

import org.deegree.commons.tom.gml.GMLObjectType;
import org.deegree.commons.tom.gml.property.Property;
import org.deegree.commons.tom.gml.property.PropertyType;
import org.deegree.feature.Feature;
import org.deegree.feature.GenericFeature;
import org.deegree.feature.property.ExtraProps;
import org.deegree.feature.types.property.GeometryPropertyType;

/**
 * Generic implementation of {@link FeatureType}, can be used for representing arbitrary feature types.
 * 
 * @author <a href="mailto:schneider@lat-lon.de">Markus Schneider </a>
 * @author last edited by: $Author:$
 * 
 * @version $Revision:$, $Date:$
 */
public class GenericFeatureType implements GMLObjectType, FeatureType {

    private QName name;

    // maps property names to their declaration (LinkedHashMap respects the correct key order)
    private Map<QName, PropertyType> propNameToDecl = new LinkedHashMap<QName, PropertyType>();

    private boolean isAbstract;

    private AppSchema schema;

    public GenericFeatureType( QName name, List<PropertyType> propDecls, boolean isAbstract ) {
        this.name = name;
        for ( PropertyType propDecl : propDecls ) {
            propNameToDecl.put( propDecl.getName(), propDecl );
        }
        this.isAbstract = isAbstract;
    }

    @Override
    public QName getName() {
        return name;
    }

    @Override
    public PropertyType getPropertyDeclaration( QName propName ) {
        return propNameToDecl.get( propName );
    }

    @Override
    public List<PropertyType> getPropertyDeclarations() {
        List<PropertyType> propDecls = new ArrayList<PropertyType>( propNameToDecl.size() );
        for ( QName propName : propNameToDecl.keySet() ) {
            propDecls.add( propNameToDecl.get( propName ) );
        }
        return propDecls;
    }

    @Override
    public GeometryPropertyType getDefaultGeometryPropertyDeclaration() {
        GeometryPropertyType geoPt = null;
        for ( QName propName : propNameToDecl.keySet() ) {
            PropertyType pt = propNameToDecl.get( propName );
            if ( pt instanceof GeometryPropertyType ) {
                geoPt = (GeometryPropertyType) pt;
                break;
            }
        }
        return geoPt;
    }

    @Override
    public Feature newFeature( String fid, List<Property> props, ExtraProps extraProps ) {
        return new GenericFeature( this, fid, props, extraProps );
    }

    @Override
    public boolean isAbstract() {
        return isAbstract;
    }

    @Override
    public AppSchema getSchema() {
        return schema;
    }

    /**
     * Sets the {@link AppSchema} that this feature type belongs to.
     * 
     * @param schema
     *            application schema, must not be <code>null</code>
     */
    void setSchema( AppSchema schema ) {
        this.schema = schema;
    }

    @Override
    public String toString() {
        String s = "- Feature type '" + name + "', abstract: " + isAbstract;
        for ( QName ptName : propNameToDecl.keySet() ) {
            PropertyType pt = propNameToDecl.get( ptName );
            s += "\n" + pt;
        }
        return s;
    }
}