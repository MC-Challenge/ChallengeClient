package net.challenge.client.ui.events

import net.challenge.client.ui.events.input.IGuiInput

/**
 * Implementation of all gui-events interfaces
 */
interface IGuiEvents : IGuiClose, IGuiInput, IRender {
}