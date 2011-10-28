/*
 * Copyright 2008-2009 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.broadleafcommerce.cms.structure.domain;

import javax.persistence.*;

import org.broadleafcommerce.presentation.AdminPresentation;
import org.broadleafcommerce.presentation.AdminPresentationClass;
import org.broadleafcommerce.presentation.PopulateToOneFieldsEnum;
import org.broadleafcommerce.presentation.RequiredOverride;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * Created by bpolster.
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "BLC_STRUCTURED_CONTENT_TYPE")
@Cache(usage= CacheConcurrencyStrategy.NONSTRICT_READ_WRITE, region="blCMSElements")
@AdminPresentationClass(populateToOneFields = PopulateToOneFieldsEnum.TRUE)
public class StructuredContentTypeImpl implements StructuredContentType {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "StructuredContentTypeId", strategy = GenerationType.TABLE)
    @TableGenerator(name = "StructuredContentTypeId", table = "SEQUENCE_GENERATOR", pkColumnName = "ID_NAME", valueColumnName = "ID_VAL", pkColumnValue = "StructuredContentTypeImpl", allocationSize = 10)
    @Column(name = "STRUCTURED_CONTENT_TYPE_ID")
    protected Long id;

    @Column (name = "NAME")
    @AdminPresentation(friendlyName="Name", order=1, group="Details", prominent=true)
    protected String name;

    @Column (name = "DESCRIPTION")
    @AdminPresentation(friendlyName="Description", order=2, group="Details",prominent=true)
    protected String description;

    @ManyToOne(targetEntity = StructuredContentFieldTemplateImpl.class)
    @JoinColumn(name="SC_FIELD_TEMPLATE_ID")
    @AdminPresentation(friendlyName="Content Template", order=2, group="Details", requiredOverride = RequiredOverride.REQUIRED, hidden = true)
    protected StructuredContentFieldTemplate structuredContentFieldTemplate;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public StructuredContentFieldTemplate getStructuredContentFieldTemplate() {
        return structuredContentFieldTemplate;
    }

    @Override
    public void setStructuredContentFieldTemplate(StructuredContentFieldTemplate scft) {
        this.structuredContentFieldTemplate = scft;
    }
}
