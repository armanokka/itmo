from __future__ import annotations

from dataclasses import dataclass, field
from typing import List, Tuple

from finite_state_machine import StateMachine, transition
from finite_state_machine.draw_state_diagram import generate_state_diagram_markdown
from finite_state_machine.exceptions import TransitionNotAllowed


SignalRecord = Tuple[str, str]


@dataclass
class RestaurantOrderFSM(StateMachine):
    """
    Управляющий конечный автомат для жизненного цикла ресторанного заказа.
    """

    state: str = "new_order"
    signals: List[SignalRecord] = field(default_factory=list)

    def _emit(self, signal: str) -> None:
        self.signals.append((self.state, signal))
        print(f"[signal] {signal} (state={self.state})")

    @transition(source="new_order", target="cooking_started")
    def start_cooking(self) -> None:
        self._emit("kitchen_ticket_sent")

    @transition(source="cooking_started", target="ready_for_serve")
    def mark_ready_for_serve(self) -> None:
        self._emit("dish_ready_notification")

    @transition(source="ready_for_serve", target="served")
    def serve_order(self) -> None:
        self._emit("order_served_notification")

    @transition(source="served", target="payment_pending")
    def request_payment(self) -> None:
        self._emit("invoice_generated")

    @transition(source="payment_pending", target="paid")
    def confirm_payment(self) -> None:
        self._emit("payment_accepted")

    @transition(
        source=["new_order", "cooking_started", "ready_for_serve", "payment_pending"],
        target="cancelled",
    )
    def cancel_order(self) -> None:
        self._emit("order_cancelled")


def run_success_scenario() -> None:
    print("\n=== Success scenario ===")
    fsm = RestaurantOrderFSM()
    print(f"Initial state: {fsm.state}")

    fsm.start_cooking()
    print(f"After start_cooking: {fsm.state}")
    fsm.mark_ready_for_serve()
    print(f"After mark_ready_for_serve: {fsm.state}")
    fsm.serve_order()
    print(f"After serve_order: {fsm.state}")
    fsm.request_payment()
    print(f"After request_payment: {fsm.state}")
    fsm.confirm_payment()
    print(f"After confirm_payment: {fsm.state}")


def run_cancel_scenario() -> None:
    print("\n=== Cancel scenario ===")
    fsm = RestaurantOrderFSM()
    print(f"Initial state: {fsm.state}")

    fsm.start_cooking()
    print(f"After start_cooking: {fsm.state}")
    fsm.cancel_order()
    print(f"After cancel_order: {fsm.state}")


def run_invalid_transition_demo() -> None:
    print("\n=== Invalid transition demo ===")
    fsm = RestaurantOrderFSM()
    try:
        fsm.confirm_payment()
    except TransitionNotAllowed as error:
        print(f"Transition rejected: {error}")


def print_state_diagram() -> None:
    print("\n=== Mermaid diagram ===")
    diagram = generate_state_diagram_markdown(RestaurantOrderFSM, initial_state="new_order")
    print(diagram)


if __name__ == "__main__":
    run_success_scenario()
    run_cancel_scenario()
    run_invalid_transition_demo()
    print_state_diagram()
