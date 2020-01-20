/**
 * The MIT License
 * Copyright (c) 2018 Estonian Information System Authority (RIA),
 * Nordic Institute for Interoperability Solutions (NIIS), Population Register Centre (VRK)
 * Copyright (c) 2015-2017 Estonian Information System Authority (RIA), Population Register Centre (VRK)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.niis.xroad.restapi.service;

import ee.ria.xroad.common.conf.serverconf.model.ClientType;
import ee.ria.xroad.common.conf.serverconf.model.EndpointType;
import ee.ria.xroad.common.conf.serverconf.model.ServiceDescriptionType;
import ee.ria.xroad.common.conf.serverconf.model.ServiceType;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EndpointService {


    public ClientType populateClientServiceDescriptionServiceEndpoints(ClientType client) {
        client.getServiceDescription().forEach(sd -> populateServiceDescriptionServiceEndpoints(sd));
        return client;
    }

    public ServiceDescriptionType populateServiceDescriptionServiceEndpoints(
            ServiceDescriptionType serviceDescription) {
        ClientType client = serviceDescription.getClient();
        serviceDescription.getService().forEach(s -> populateServiceEndpoints(s, client));
        return serviceDescription;
    }

    public ServiceType populateServiceEndpoints(ServiceType service, ClientType client) {
        List<EndpointType> endpoints = client.getEndpoint();
        endpoints.forEach(endpointType -> {
            if (endpointType.getServiceCode().equals(service.getServiceCode())) {
                service.getEndpoint().add(endpointType);
            }
        });
        return service;
    }

}
