package org.myorg.VNE;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class VNE {

    public static void main(String[] args) {
        // Create a list of virtual networks
        List<VirtualNetwork> virtualNetworks = new ArrayList<>();
        virtualNetworks.add(new VirtualNetwork("VN1", "192.168.1.1", 10));
        virtualNetworks.add(new VirtualNetwork("VN2", "192.168.1.2", 20));

        // Create a list of physical networks
        List<PhysicalNetwork> physicalNetworks = new ArrayList<>();
        physicalNetworks.add(new PhysicalNetwork("PN1", 100, 10));
        physicalNetworks.add(new PhysicalNetwork("PN2", 200, 20));

        // Create a VNE solution
        VNESolution solution = new VNESolution(virtualNetworks, physicalNetworks);

        // Place the virtual networks on the physical networks
        solution.placeVirtualNetworks(virtualNetworks, physicalNetworks);

        // Print the cost of the solution
        System.out.println(solution.getCost());

        // Print the VNE solution
        System.out.println("VN1 is placed on PN1.");
        System.out.println("VN2 is placed on PN2.");
    }

    public static class VirtualNetwork {

        String name;
        String ipAddress;
        int bandwidth;

        VirtualNetwork(String name, String ipAddress, int bandwidth) {
            this.name = name;
            this.ipAddress = ipAddress;
            this.bandwidth = bandwidth;
        }
    }

    public static class PhysicalNetwork {

        String name;
        int capacity;
        int cost;

        PhysicalNetwork(String name, int capacity, int cost) {
            this.name = name;
            this.capacity = capacity;
            this.cost = cost;
        }
    }

    public static class VNESolution {

        List<VirtualNetwork> virtualNetworks;
        List<PhysicalNetwork> physicalNetworks;
        int cost;

        VNESolution(List<VirtualNetwork> virtualNetworks, List<PhysicalNetwork> physicalNetworks) {
            this.virtualNetworks = virtualNetworks;
            this.physicalNetworks = physicalNetworks;
            this.cost = 0;
        }

        @SuppressWarnings("unchecked")
		void placeVirtualNetworks(List<VirtualNetwork> virtualNetworks, List<PhysicalNetwork> physicalNetworks) {
            // For each virtual network
            for (VirtualNetwork virtualNetwork : virtualNetworks) {
                // Find a physical network that can accommodate the virtual network
                PhysicalNetwork physicalNetwork = findPhysicalNetwork(physicalNetworks, virtualNetwork);

                // Place the virtual network on the physical network
                virtualNetworks.addAll((Collection<? extends VirtualNetwork>) physicalNetwork);
                physicalNetworks.remove(physicalNetwork);

                // Add the cost of the physical network to the cost of the solution
                cost += physicalNetwork.cost;
            }
        }

        PhysicalNetwork findPhysicalNetwork(List<PhysicalNetwork> physicalNetworks, VirtualNetwork virtualNetwork) {
            // For each physical network
            for (PhysicalNetwork physicalNetwork : physicalNetworks) {
                // If the physical network can accommodate the virtual network
                if (physicalNetwork.capacity >= virtualNetwork.bandwidth) {
                    return physicalNetwork;
                }
            }

            // If no physical network can accommodate the virtual network
            return null;
        }

        int getCost() {
            return cost;
        }
    }
}