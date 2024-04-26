/**
 * The {@code OrderStatus} enum represents the various statuses an order can have in the system.
 * It includes:
 * <ul>
 *     <li>{@code PROCESSING} - The order is currently being processed.</li>
 *     <li>{@code READY_TO_PICKUP} - The order is ready for pickup.</li>
 *     <li>{@code CANCELLED} - The order has been cancelled.</li>
 *     <li>{@code COLLECTED} - The order has been collected by the customer.</li>
 * </ul>
 */
package Enums;

public enum OrderStatus {
	
	PROCESSING,
	
	READY_TO_PICKUP,
	
	CANCELLED,
	
	COMPLETED

}
