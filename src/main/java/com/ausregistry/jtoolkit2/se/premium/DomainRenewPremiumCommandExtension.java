package com.ausregistry.jtoolkit2.se.premium;

import java.math.BigDecimal;

import org.w3c.dom.Element;

import com.ausregistry.jtoolkit2.se.Command;
import com.ausregistry.jtoolkit2.se.CommandExtension;
import com.ausregistry.jtoolkit2.se.ExtendedObjectType;
import com.ausregistry.jtoolkit2.xml.XMLWriter;

/**
 * <p>Extension for the EPP Domain Renew command, representing the Renew Premium Domain aspect of the
 * Premium Domain Name extension.</p>
 *
 * <p>Use this to acknowledge the premium fees associated with this domain name as part of an EPP Domain Renew
 * command compliant with RFC5730 and RFC5731. The "renewal price" value is optional, but if it is
 * supplied, should match the renewal fee that is set for the domain name for the requested period.
 * The response expected from a server should be handled by a Domain Renew Response object.</p>
 *
 * @see com.ausregistry.jtoolkit2.se.DomainRenewCommand
 * @see com.ausregistry.jtoolkit2.se.DomainRenewResponse
 * @see <a href="http://ausregistry.github.io/doc/premium-1.0/premium-1.0.html">Premium Domain Name Extension
 * Mapping for the Extensible Provisioning Protocol (EPP)</a>
 */
public class DomainRenewPremiumCommandExtension implements CommandExtension {

    private static final long serialVersionUID = 3132637983358135984L;

    private BigDecimal renewalPrice;

    @Override
    public void addToCommand(Command command) {
        final XMLWriter xmlWriter = command.getXmlWriter();
        final Element extensionElement = command.getExtensionElement();
        final Element renewElement = xmlWriter.appendChild(extensionElement, "renew",
                ExtendedObjectType.PREMIUM.getURI());
        Element ackElement = xmlWriter.appendChild(renewElement, "ack");

        if (renewalPrice != null) {
            xmlWriter.appendChild(ackElement, "renewalPrice").setTextContent(renewalPrice.toPlainString());
        }
    }

    public void setRenewalPrice(BigDecimal renewalPrice) {
        this.renewalPrice = renewalPrice;
    }
}
